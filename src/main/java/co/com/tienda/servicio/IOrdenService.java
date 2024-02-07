package co.com.tienda.servicio;

import java.util.List;

import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Usuario;

public interface IOrdenService {
    
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
}
