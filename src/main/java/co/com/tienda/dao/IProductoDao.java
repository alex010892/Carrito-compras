package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.domain.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{
    
}
