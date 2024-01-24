package co.com.tienda.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orden")
public class Orden {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;

    private Date fechaCreacion;

    private Date fechaRecibida;

    private double total;

    @ManyToOne
    private Cliente cliente;

    @OneToOne(mappedBy = "orden")   
    private DetalleOrden detalle;
}
