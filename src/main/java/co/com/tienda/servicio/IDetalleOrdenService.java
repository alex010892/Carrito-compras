package co.com.tienda.servicio;

import java.util.Optional;

import co.com.tienda.domain.DetalleOrden;
import co.com.tienda.domain.Orden;

public interface IDetalleOrdenService {
    
    DetalleOrden save(DetalleOrden detalleOrden);

    Optional<DetalleOrden> findById(Integer id);
}
