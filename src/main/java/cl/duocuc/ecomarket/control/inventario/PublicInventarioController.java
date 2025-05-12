package cl.duocuc.ecomarket.control.inventario;

import cl.duocuc.ecomarket.modelo.dto.inventario.*;
import cl.duocuc.ecomarket.servicio.ServicioInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/inventario")

public class PublicInventarioController {

    private final ServicioInventario service;

    // Constructor con inyección de dependencias
    @Autowired
    public PublicInventarioController(ServicioInventario service){
        this.service = service;
    }

    // ==============================
    // Endpoints para Inventario
    // ==============================

    @GetMapping("/Inventario/{id}")
    public ResponseEntity<InventarioResponseDTO> consultar(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerInventario(id));
    }

    @PostMapping("/Inventario")
    public ResponseEntity<InventarioResponseDTO> crear(@RequestBody InventarioRequestDTO inventario){
        InventarioResponseDTO creado = service.crearInventario(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/Inventario/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody InventarioRequestDTO inventario) {
        service.actualizarInventario(id, inventario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/Inventario/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Long id){
        service.desactivarInventario(id);
        return ResponseEntity.status(204).body(String.format("Inventario %d desactivado correctamente", id));
    }
    // ==============================
    // Endpoints para Bodega
    // ==============================

    @GetMapping("/bodega/{id}")
    public ResponseEntity<BodegaResponseDTO> consultarBodega(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerBodega(id));
    }

    @PostMapping("/bodega")
    public ResponseEntity<BodegaResponseDTO> crearBodega(@RequestBody BodegaRequestDTO bodega){
        BodegaResponseDTO creada = service.crearBodega(bodega);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/bodega/{id}")
    public ResponseEntity<Void> actualizarBodega(@PathVariable Long id, @RequestBody BodegaUpdateDTO bodega) {
        service.actualizarBodega(id, bodega);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/bodega/{id}")
    public ResponseEntity<String> desactivarBodega(@PathVariable Long id){
        service.desactivarBodega(id);
        return ResponseEntity.status(204).body(String.format("Bodega %d desactivada correctamente", id));
    }

    @GetMapping("/bodega")
    public ResponseEntity<List<BodegaResponseDTO>> listarBodegas(){
        return ResponseEntity.ok(service.listarBodegas());
    }



}