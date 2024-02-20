package co.com.tienda.servicio;

import java.util.List;

//import co.com.tienda.domain.Cliente;
import co.com.tienda.domain.Usuario;

public interface IUsuarioService {
    
    public Usuario encontrarUsuario();

    public Usuario guardar(Usuario usuario);

    public List<Usuario> encontrarUsuarios();
}
