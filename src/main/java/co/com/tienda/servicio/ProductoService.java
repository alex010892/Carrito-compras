package co.com.tienda.servicio;

import java.util.List;

import co.com.tienda.domain.Producto;

public interface ProductoService {
    
    public List<Producto> listarProductos();
    
    public void guardar(Producto producto);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProducto(Producto producto);
}
