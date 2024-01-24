package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.domain.Producto;

public interface ProductoDao extends JpaRepository<Producto, Long>{
    
}
