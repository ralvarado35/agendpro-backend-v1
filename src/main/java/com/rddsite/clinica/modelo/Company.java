
package com.rddsite.clinica.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
@Entity
@Table(name="companies")
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_company", nullable=false)
    private Long id;
    
    private String name;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String industry;
    private Date foundation_date;
    private String description;
    private Integer number_employes;
    private String country;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Service> services = new HashSet<>();

    
}
