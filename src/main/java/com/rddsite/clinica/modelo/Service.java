
package com.rddsite.clinica.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="services")
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_service", nullable=false)
    private Long id;
      
    private String name;   
    private Integer state;
        
    
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @JoinColumn(name="id_company", referencedColumnName = "id_company")   
    private Company company  ;

      
  
}
