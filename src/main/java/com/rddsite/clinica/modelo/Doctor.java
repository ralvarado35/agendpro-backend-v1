
package com.rddsite.clinica.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="doctores")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_doctor", nullable=false)
    private int id;
    
    private String nombre;
    private String apellidos;   
    private String sexo;
    private String telefono;
    private String email;
    
    
    
}
