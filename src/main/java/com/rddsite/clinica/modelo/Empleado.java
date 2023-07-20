
package com.rddsite.clinica.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="empleados")
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empleado", nullable=false)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String email;
    
            
    
}
