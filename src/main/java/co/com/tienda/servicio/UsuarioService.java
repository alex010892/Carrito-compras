package co.com.tienda.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import co.com.tienda.dao.IClienteDao;
import co.com.tienda.dao.IUsuarioDao;
//import co.com.tienda.domain.Cliente;
import co.com.tienda.domain.Rol;
import co.com.tienda.domain.Usuario;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService, IUsuarioService {

    @Autowired
    private IUsuarioDao usuarioDao;

    // @Autowired
    // private IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombrerol()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

    // @Override
    // public Usuario encontrarUsuario(Usuario usuario) {
    // // TODO Auto-generated method stub
    // return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    // }

    @Override
    public Usuario encontrarUsuario() {
        // Obtener el nombre de usuario del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Buscar y devolver el usuario por nombre de usuario
        return usuarioDao.findByUsername(username);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        // TODO Auto-generated method stub
        return usuarioDao.save(usuario);
    }

}
