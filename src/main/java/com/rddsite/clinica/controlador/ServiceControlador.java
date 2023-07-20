package com.rddsite.clinica.controlador;


import com.rddsite.clinica.modelo.Company;
import com.rddsite.clinica.modelo.Service;
import com.rddsite.clinica.modelo.Service;
import com.rddsite.clinica.modelo.Detalle;
import com.rddsite.clinica.repositorio.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rddsite.clinica.repositorio.ServiceRepository;
import com.rddsite.excepciones.ResourcesNotFoundException;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
public class ServiceControlador {

    @Autowired
    private ServiceRepository serviceRepository;
  
       //Este metodo sirve para lista company   
    @GetMapping("/services")
    public List<Service> listAllServices() {
        return serviceRepository.findAll();
    }
    
   //Este metodo sirve para obtener un service por id
    @GetMapping("services/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe registro: " + id));
        return ResponseEntity.ok(service);
    }

    // Este metodo sirve para guadar service
    @PostMapping(value = "/services", consumes = {"application/json"})
    public Service saveService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    // Este metodo sirve para editar una service
    @PutMapping("services/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service detailsService) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("El registro no existe: " + id));

        service.setName(detailsService.getName());       

        Service updatedService = serviceRepository.save(service);
        return ResponseEntity.ok(updatedService);

    }

    //este metodo sirve para eliminar un empleado
    @DeleteMapping("/services/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe el registro con el ID : " + id));

        serviceRepository.delete(service);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
    
    
}
