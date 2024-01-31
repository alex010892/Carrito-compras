package co.com.tienda.domain;

import java.io.Serializable;
import javax.persistence.*;
//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String descripcion;
    
    // @NotEmpty
    // @Email
    // private String email;
    
    // private String telefono;

    // @NotEmpty
    private double precio;

    @NotNull //notnull es para cualquier tipo diferente a String, notempty es para tipo String solamente
    private int stock;

    @ManyToOne
    private Usuario usuario;

    // @OneToMany
    // private DetalleOrden detalle; //es opcional colocar esto, se tendria que hacer un mapeo ...
}
