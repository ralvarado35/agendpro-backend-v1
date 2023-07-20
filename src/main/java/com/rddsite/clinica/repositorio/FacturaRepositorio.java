
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura, Long>{
    
}
