
    package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long>{
    
}
