
package com.rddsite.clinica.modelo;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Entity
@Table(name="facturas")
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_factura", nullable=false)
    private Long id;
      
    private String nombre;   
    private String direccion;
    private String nit;
    private Date fecha;    
    private Double total; 

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private Set<Detalle> detalles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<Detalle> detalles) {
        this.detalles = detalles;
        for(Detalle detalle : detalles){
            detalle.setFactura(this);
        }
    }

   
    
    
    
}
