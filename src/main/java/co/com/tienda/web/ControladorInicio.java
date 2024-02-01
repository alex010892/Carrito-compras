package co.com.tienda.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import co.com.tienda.domain.Cliente;
import co.com.tienda.domain.DetalleOrden;
import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Producto;
import co.com.tienda.domain.Usuario;
import co.com.tienda.servicio.IUsuarioService;
import co.com.tienda.servicio.IDetalleOrdenService;
import co.com.tienda.servicio.IOrdenService;
import co.com.tienda.servicio.IProductoService;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private IProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;

    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

    //Cliente cliente = new Cliente();
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var productos = productoService.listarProductos();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("productos", productos);
        var stockTotal = 0;
        for(var p: productos){
            stockTotal += p.getStock();
        }
        model.addAttribute("stockTotal", stockTotal);
        model.addAttribute("totalProductos", productos.size());
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Producto producto){
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        productoService.guardar(producto);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idProducto}")
    public String editar(Producto producto, Model model){
        producto = productoService.encontrarProducto(producto);
        model.addAttribute("producto", producto);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Producto producto){
        productoService.eliminar(producto);
        return "redirect:/";
    }

    @GetMapping("/eliminar/carrito/{idProducto}")
    public String borrarProductoCarrito(Producto producto, Model model){
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

        for(DetalleOrden detalleOrden : detalles){
            if(detalleOrden.getProducto().getIdProducto() != producto.getIdProducto()){
                ordenesNuevas.add(detalleOrden);
            }
        }

        //poner la nueva lista con los productos restantes
        detalles = ordenesNuevas;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);


        return "carrito";
    }

    // @GetMapping("/carrito")
    // public String verCarrito(){
    //     return "carrito";
    // }

    @GetMapping("/carrito")
    public String verCarrito(Model model) {
        // Puedes colocar aquí lógica adicional si es necesario antes de mostrar el carrito.
        model.addAttribute("carrito", detalles); // Detalles se refiere a la lista de detalles del carrito.
        model.addAttribute("orden", orden); // Orden se refiere al objeto de orden.

        return "carrito"; // Devuelve el nombre de la plantilla que mostrará el carrito.
    }

    @PostMapping("/carrito")
    public String addCarrito(Producto producto, Integer cantidad, Model model){
        DetalleOrden detalleOrden = new DetalleOrden();
        //Producto producto = new Producto();
        double sumaTotal = 0;

        producto = productoService.encontrarProducto(producto);
        System.out.println("producto añadido: " + producto.toString());
        System.out.println("cantidad: " + cantidad);
        //producto = producto.toString();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        //validar que el producto no se añada 2 veces
        Long idProducto = producto.getIdProducto();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getIdProducto() == idProducto);

        if(!ingresado){
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "carrito";
    }

    // @GetMapping("/orden")
    // public String orden(Model model, Usuario usuario){

    //     //usuario = usuarioService.encontrarUsuario(usuario);

    //     model.addAttribute("carrito", detalles);
    //     model.addAttribute("orden", orden);
    //     model.addAttribute("usuario", usuario);
    //     return "resumenorden";
    // }

    @GetMapping("/orden")
    public String orden(Model model, Usuario usuario) {
        // Obtener el usuario actual
         usuario = usuarioService.encontrarUsuario();

        // Lógica para obtener detalles del carrito y orden (sustituir por tu propia lógica)
        //List<DetalleCarrito> detalles = obtenerDetallesOrden(); // Supongamos que tienes una función para obtener detalles del carrito
        //Orden orden = obtenerOrden(); // Supongamos que tienes una función para obtener detalles de la orden

        // Agregar atributos al modelo
        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "resumenorden";
    }

    //guardar la orden
    @GetMapping("/guardarOrden")
    public String guardarOrden(){
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        //usuario
        Usuario usuario = usuarioService.encontrarUsuario();
         
        orden.setUsuario(usuario);
        ordenService.save(orden);

        //guardar detalles
        for(DetalleOrden dt: detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        //limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    @PostMapping("/buscar")
    public String buscarProducto(@RequestParam String nombre, Model model){ //la variable nombre debe ser igual a la variable name="" en la vista html en archivo plantilla.html, header, form, debe estar dentro de una form para enviar una petición Post
    List<Producto> productos = productoService.listarProductos().stream().filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase())).collect(Collectors.toList());
    model.addAttribute("productos", productos);
    log.info("Nombre del producto: {}", nombre);
        return "index";
    }
}
