package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.tienda.domain.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
}
