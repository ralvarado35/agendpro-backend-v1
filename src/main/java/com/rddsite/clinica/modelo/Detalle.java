
package com.rddsite.clinica.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="detalles")
public class Detalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_detalle", nullable=false)
    private Long id;
      
    private String producto;   
    private Integer cantidad;
    private Double sub_total;     
    
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @JoinColumn(name="id_factura", referencedColumnName = "id_factura")   
    private Factura factura  ;

      
  
}
