
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long>{
    
}
