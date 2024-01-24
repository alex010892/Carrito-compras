package co.com.tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
}
