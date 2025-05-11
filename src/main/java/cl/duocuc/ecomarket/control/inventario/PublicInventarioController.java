package cl.duocuc.ecomarket.control.inventario;

import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.servicio.ServicioInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/inventario")

public class PublicInventarioController {

    private final ServicioInventario service;

    // Constructor con inyección de dependencias
    @Autowired
    public PublicInventarioController(ServicioInventario service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> consultar(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerInventario(id));
    }

//    @PostMapping("/")
//    public ResponseEntity<InventarioResponseDTO> crear(@RequestBody InventarioRequestDTO inventario){
//        return ResponseEntity.status(201).body(service.crearInventario(inventario));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody InventarioRequestDTO inventario) {
//        service.actualizarInventario(id, inventario);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> desactivar(@PathVariable Long id){
//        service.desactivarInventario(id);
//        return ResponseEntity.status(204).body(String.format("Inventario %d desactivado correctamente", id));
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<List<InventarioResponseDTO>> listarTodos(){
//        return ResponseEntity.ok(service.listarInventarios());
//    }
}