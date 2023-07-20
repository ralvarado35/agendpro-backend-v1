package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Doctor;
import com.rddsite.clinica.repositorio.DoctorRepositorio;
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
public class DoctorControlador {
        
    @Autowired
    private DoctorRepositorio repositorio;
    
    // Este metodo sirve para lista todos los doctores    
    @GetMapping("/doctores")
    public List<Doctor> listarTodosLosDoctores(){
        return repositorio.findAll();
    }
   
    // Este metodo sirve para guardar el empleado
    @PostMapping(value = "/doctores",  consumes = {"application/json"})
    public Doctor guardarDoctor(@RequestBody Doctor doctor) {
        return repositorio.save(doctor);
    }
    
    //Este metodo sirve para obtener un doctor por id
    @GetMapping("doctores/{id}")
    public ResponseEntity<Doctor> obtenerDoctorPorId(@PathVariable Long id){
        Doctor doctor = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No existe el doctor: "+ id ));
        
        return ResponseEntity.ok(doctor);
                           
    }
    
     @PutMapping("doctores/{id}")
     public ResponseEntity<Doctor> actualizarDoctor(@PathVariable Long id, @RequestBody Doctor detallesDoctor){
        Doctor doctor = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No exise el doctor: "+ id ));
        
        doctor.setNombre(detallesDoctor.getNombre());
        doctor.setApellidos(detallesDoctor.getApellidos());
        doctor.setSexo(detallesDoctor.getSexo());
        doctor.setTelefono(detallesDoctor.getTelefono());
        doctor.setEmail(detallesDoctor.getEmail());
        
        Doctor doctorActualizado = repositorio.save(doctor);        
        return ResponseEntity.ok(doctorActualizado);
                           
    }
    
    
    //este metodo sirve para eliminar un empleado
	@DeleteMapping("/doctores/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDoctor(@PathVariable Long id){
		Doctor doctor = repositorio.findById(id)
				            .orElseThrow(() -> new ResourcesNotFoundException("No existe el doctor con el ID : " + id));
		
		repositorio.delete(doctor);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    
}
