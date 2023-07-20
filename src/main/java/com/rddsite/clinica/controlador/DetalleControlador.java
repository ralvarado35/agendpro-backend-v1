package com.rddsite.clinica.controlador;

import com.rddsite.clinica.modelo.Cita;
import com.rddsite.clinica.modelo.Detalle;
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
@RequestMapping("/api/v1/detalles")
@CrossOrigin(origins = "http://localhost:4200/")
public class DetalleControlador {

    @Autowired
    private DetalleRepositorio detalleRepositorio;

    @Autowired
    private FacturaRepositorio facturaRepositorio;
    
    @GetMapping
    public ResponseEntity<Page<Detalle>> listarDetalles(Pageable pageable){
        return ResponseEntity.ok(detalleRepositorio.findAll(pageable));
    }


    @PostMapping
    public ResponseEntity<Detalle> guardarDetalle(@Valid @RequestBody Detalle detalle) {
        Optional<Factura> facturaOptional = facturaRepositorio.findById(detalle.getFactura().getId());

        if (!facturaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        detalle.setFactura(facturaOptional.get());
        Detalle detalleGuardado = detalleRepositorio.save(detalle);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(detalleGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(detalleGuardado);
    }

    @PutMapping
    public ResponseEntity<Detalle> actualizarDetalle(@PathVariable Long id, @Valid @RequestBody Detalle detalle) {
        Optional<Factura> facturaOptional = facturaRepositorio.findById(detalle.getFactura().getId());

        if (!facturaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Detalle> detalleOptional = detalleRepositorio.findById(id);
        if (!detalleOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        detalle.setFactura(facturaOptional.get());
        detalle.setId(detalleOptional.get().getId());
        detalleRepositorio.save(detalle);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Detalle> actualizarDetalle(@PathVariable Long id) {
        Optional<Detalle> detalleOptional = detalleRepositorio.findById(id);
        
        if (!detalleOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        detalleRepositorio.delete(detalleOptional.get());
        return ResponseEntity.noContent().build();

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Detalle> obtenerDetallePorId(@PathVariable Long id){
        
         Optional<Detalle> detalleOptional = detalleRepositorio.findById(id);
        
        if (!detalleOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        
        return ResponseEntity.ok(detalleOptional.get());
    }
    
    
}
