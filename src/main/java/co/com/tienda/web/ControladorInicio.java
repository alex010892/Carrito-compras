package co.com.tienda.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import co.com.tienda.domain.DetalleOrden;
import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Producto;
import co.com.tienda.servicio.ProductoService;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private ProductoService productoService;

    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();
    
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

    @PostMapping("/carrito")
    public String addCarrito(Producto producto, Integer cantidad){
        DetalleOrden detalleOrden = new DetalleOrden();
        //Producto producto = new Producto();
        double sumaTotal = 0;

        producto = productoService.encontrarProducto(producto);
        System.out.println("producto añadido: " + producto.toString());
        System.out.println("cantidad: " + cantidad);
        return "carrito";
    }
}
