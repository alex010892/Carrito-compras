package co.com.tienda.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;

    private String nombre;

    private String email;

    private String direccion;

    private String nombrerol;

    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    
    // @OneToMany
    // @JoinColumn(name="id_usuario")
    // private List<Rol> roles;

    @OneToMany(mappedBy="usuario")
    private List<Rol> roles;

}
