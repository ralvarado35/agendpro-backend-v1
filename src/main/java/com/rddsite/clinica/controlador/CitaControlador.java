package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Cita;
import com.rddsite.clinica.repositorio.CitaRepositorio;
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
public class CitaControlador {
        
    @Autowired
    private CitaRepositorio repositorio;
    
    // Este metodo sirve para lista todos los citaes    
    @GetMapping("/citas")
    public List<Cita> listarTodosLosCitaes(){
        return repositorio.findAll();
    }
   
    // Este metodo sirve para guardar el empleado
    @PostMapping("/citas")
    public Cita guardarCita(@RequestBody Cita cita) {
        return repositorio.save(cita);
    }
    
    //Este metodo sirve para obtener un cita por id
    @GetMapping("citas/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable Long id){
        Cita cita = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No existe el cita: "+ id ));
        
        return ResponseEntity.ok(cita);
                           
    }
    
     @PutMapping("citas/{id}")
     public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita detallesCita){
        Cita cita = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No exise el cita: "+ id ));
        
        cita.setFecha_cita(detallesCita.getFecha_cita());
        cita.setHora_cita(detallesCita.getHora_cita());
        cita.setId_paciente(detallesCita.getId_paciente());
        cita.setId_medico(detallesCita.getId_medico());
        cita.setEstado(detallesCita.getEstado());
        cita.setMotivo_cita(detallesCita.getMotivo_cita());
        cita.setDiagnostico(detallesCita.getDiagnostico());
        cita.setReceta(detallesCita.getReceta());
                
        Cita citaActualizado = repositorio.save(cita);        
        return ResponseEntity.ok(citaActualizado);
                           
    }
    
    
    //este metodo sirve para eliminar un empleado
	@DeleteMapping("/citas/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarCita(@PathVariable Long id){
		Cita cita = repositorio.findById(id)
				            .orElseThrow(() -> new ResourcesNotFoundException("No existe el cita con el ID : " + id));
		
		repositorio.delete(cita);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    
}
