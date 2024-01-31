package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.DetalleOrden;

@Repository
public interface IDetalleOrdenDao extends JpaRepository<DetalleOrden, Long>{
    
}
