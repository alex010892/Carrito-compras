package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.Orden;

@Repository
public interface IOrdenDao extends JpaRepository<Orden, Long>{
    
}
