
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>{
    
}
