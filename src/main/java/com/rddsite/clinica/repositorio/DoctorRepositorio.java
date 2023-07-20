
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepositorio extends JpaRepository<Doctor, Long>{
    
}
