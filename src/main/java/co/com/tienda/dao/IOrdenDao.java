package co.com.tienda.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.Orden;
import co.com.tienda.domain.Usuario;

@Repository
public interface IOrdenDao extends JpaRepository<Orden, Integer>{
    
    List<Orden> findByUsuario(Usuario usuario);
    //Optional<Orden> findById(Integer id);
}
