
    package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long>{
    
}
