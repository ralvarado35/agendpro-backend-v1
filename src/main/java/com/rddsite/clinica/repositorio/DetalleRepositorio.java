
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepositorio extends JpaRepository<Detalle, Long>{
    
}
