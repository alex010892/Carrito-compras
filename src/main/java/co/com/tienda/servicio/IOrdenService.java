package co.com.tienda.servicio;

import java.util.List;

import co.com.tienda.domain.Orden;

public interface IOrdenService {
    
    List<Orden> findAll();
    Orden save(Orden orden);
}
