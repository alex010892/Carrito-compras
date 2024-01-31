package co.com.tienda.servicio;

import java.util.*;

import co.com.tienda.domain.Producto;

public interface IProductoService {
    
    public List<Producto> listarProductos();
    
    public void guardar(Producto producto);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProducto(Producto producto);

    //public Optional<Producto> get(Long id);
}
