package co.com.tienda.servicio;

import java.util.List;
import java.util.Optional;

import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Usuario;

public interface IOrdenService {
    
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
}
