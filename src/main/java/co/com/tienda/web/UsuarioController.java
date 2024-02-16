package co.com.tienda.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.tienda.domain.DetalleOrden;
import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Rol;
import co.com.tienda.domain.Usuario;
import co.com.tienda.servicio.IOrdenService;
import co.com.tienda.servicio.IRolService;
import co.com.tienda.servicio.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired 
    private IRolService rolService;

    @Autowired
    private IOrdenService ordenService;

    List<Rol> roles = new ArrayList<Rol>();

    //Rol rol = new Rol();

    @GetMapping("/registro")
    public String create(){
        return "registro";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") Usuario usuario){
        //guardar usuario
        // Usuario usuario = new Usuario();
        // usuario.setNombre(usuarioForm.getNombre());
        // usuario.setUsername(usuarioForm.getUsername());
        // usuario.setEmail(usuarioForm.getEmail());
        // usuario.setDireccion(usuarioForm.getDireccion());
        // usuario.setPassword(usuarioForm.getPassword());

        // Crear un objeto Rol a partir de los datos del formulario
        Rol rol = new Rol();
        rol.setNombrerol(usuario.getNombrerol());

        // Establecer la relación entre Usuario y Rol (si es necesario)
        // usuario.setRol(rol); 

        // Establecer la relación entre Usuario y Rol
        rol.setUsuario(usuario);

        usuarioService.guardar(usuario);

        //guardar rol
        rolService.guardar(rol);

        //guardar roles
        // for(Rol rl: roles){
        //     //rl.setUsuario(usuario);
        //     rolService.guardar(rl);
        // }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model){
        Usuario usuario = usuarioService.encontrarUsuario();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);
        //model.addAttribute("usuario", usuario);
        return "compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, Model model){
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        return "detallecompra";
    }
}
