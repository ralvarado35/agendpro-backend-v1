package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Cita;
import com.rddsite.clinica.modelo.Detalle;
import com.rddsite.clinica.modelo.Doctor;
import com.rddsite.clinica.modelo.Factura;
import com.rddsite.clinica.repositorio.DetalleRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rddsite.clinica.repositorio.FacturaRepositorio;
import com.rddsite.excepciones.ResourcesNotFoundException;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/api/v1/facturas")
@CrossOrigin(origins = "http://localhost:4200/")
public class FacturaControlador {

    @Autowired
    private FacturaRepositorio facturaRepositorio;
    
//    @GetMapping
//    public ResponseEntity<Page<Factura>> listarFacturas(Pageable pageable){
//        return ResponseEntity.ok(facturaRepositorio.findAll(pageable));
//        
//    }
    
    // Este metodo sirve para lista todos los doctores    
    @GetMapping("")
    public List<Factura> listarTodasLasFacturas(){
        return facturaRepositorio.findAll();
    }

    @PostMapping
    public ResponseEntity<Factura> guardarFactura(@Valid @RequestBody Factura factura) {
        Factura facturaGuardada = facturaRepositorio.save(factura);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(facturaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(facturaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizarFactura(@PathVariable Long id, @Valid @RequestBody Factura factura) {
        Optional<Factura> facturaOptional = facturaRepositorio.findById(id);
        if (!facturaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        factura.setId(facturaOptional.get().getId());
        facturaRepositorio.save(factura);
        return ResponseEntity.noContent().build();

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> eliminarFactura(@PathVariable Long id){
        Optional<Factura> facturaOptional = facturaRepositorio.findById(id);
        if (!facturaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        facturaRepositorio.delete(facturaOptional.get());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable Long id){
        Optional<Factura> facturaOptional = facturaRepositorio.findById(id);
        
        if (!facturaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(facturaOptional.get());        
    }
    
    
}
