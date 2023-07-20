
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
@Table(name="pacientes")
public class Paciente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente", nullable=false)
    private int id;
    
    private String nombre;
    private String apellidos;   
    private String fecha_nac;
    private String dui;
    private String sexo;
    private String telefono;
    private String email;
    
    
    
    
}
