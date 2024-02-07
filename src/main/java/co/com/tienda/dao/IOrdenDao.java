package co.com.tienda.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Usuario;

@Repository
public interface IOrdenDao extends JpaRepository<Orden, Long>{
    
    List<Orden> findByUsuario(Usuario usuario);
}
