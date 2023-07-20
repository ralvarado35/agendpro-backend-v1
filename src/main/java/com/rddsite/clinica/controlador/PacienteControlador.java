
package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Paciente;
import com.rddsite.clinica.repositorio.PacienteRepositorio;
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
public class PacienteControlador {
    
    @Autowired
    private PacienteRepositorio repositorio;
    
    
    // Este metodo sirve para lista todos los pacientes    
    @GetMapping("/pacientes")
    public List<Paciente> listarTodosLosPacientes(){
        return repositorio.findAll();
    }
    
    // Este metodo sirve para guardar el paciente
    @PostMapping("/pacientes")
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return repositorio.save(paciente);
    }
    
    //Este metodo sirve para obtener un paciente por id
    @GetMapping("pacientes/{id}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Long id){
        Paciente paciente = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No exise el paciente: "+ id ));
        
        return ResponseEntity.ok(paciente);
                           
    }
    
    @PutMapping("pacientes/{id}")
     public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente detallesPaciente){
        Paciente paciente = repositorio.findById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("No exise el paciente: "+ id ));
        
        paciente.setNombre(detallesPaciente.getNombre());
        paciente.setApellidos(detallesPaciente.getApellidos());
        paciente.setFecha_nac(detallesPaciente.getFecha_nac());
        paciente.setDui(detallesPaciente.getDui());
        paciente.setSexo(detallesPaciente.getSexo());
        paciente.setTelefono(detallesPaciente.getTelefono());
        paciente.setEmail(detallesPaciente.getEmail());
        
        Paciente pacienteActualizado = repositorio.save(paciente);        
        return ResponseEntity.ok(pacienteActualizado);
                           
    }
    
     //este metodo sirve para eliminar un paciente
	@DeleteMapping("/pacientes/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarPaciente(@PathVariable Long id){
		Paciente paciente = repositorio.findById(id)
				            .orElseThrow(() -> new ResourcesNotFoundException("No existe el paciente con el ID : " + id));
		
		repositorio.delete(paciente);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    
}
