
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
@Table(name="citas")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cita", nullable=false)
    private int id;
    
    private String fecha_cita;
    private String hora_cita;   
    private int id_paciente;
    private int id_medico;
    private String estado;
    private String motivo_cita;
    private String diagnostico;
    private String receta;
    
    
}
