
package com.rddsite.clinica.repositorio;

import com.rddsite.clinica.modelo.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
    
}
