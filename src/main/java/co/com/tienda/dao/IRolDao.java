package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.Rol;

@Repository
public interface IRolDao extends JpaRepository<Rol, Long>{
    
}
