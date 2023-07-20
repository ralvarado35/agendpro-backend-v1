package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Cita;
import com.rddsite.clinica.modelo.Company;
import com.rddsite.clinica.modelo.Doctor;
import com.rddsite.clinica.repositorio.CompanyRepository;
import com.rddsite.excepciones.ResourcesNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class CompanyControlador {

    @Autowired
    private CompanyRepository companyRepository;

    //Este metodo sirve para lista company   
    @GetMapping("/companies")
    public List<Company> listarTodasLasCompanies() {
        return companyRepository.findAll();
    }

    //Este metodo sirve para obtener un company por id
    @GetMapping("companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe registro: " + id));
        return ResponseEntity.ok(company);
    }

    // Este metodo sirve para guadar company
    @PostMapping(value = "/companies", consumes = {"application/json"})
    public Company saveCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    // Este metodo sirve para editar una company
    @PutMapping("companies/{id}")
    public ResponseEntity<Company> update(@PathVariable Long id, @RequestBody Company detailsCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("El registro no existe: " + id));

        company.setName(detailsCompany.getName());
        company.setAddress(detailsCompany.getAddress());
        company.setPhone(detailsCompany.getPhone());
        company.setEmail(detailsCompany.getEmail());

        Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(updatedCompany);

    }

    //este metodo sirve para eliminar un empleado
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe el registro con el ID : " + id));

        companyRepository.delete(company);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
