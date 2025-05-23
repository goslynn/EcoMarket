package cl.duocuc.ecomarket.control.inventario;

import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.BodegaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.FamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.FamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.ProductoResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SucursalResponseDTO;

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
    public PublicInventarioController(ServicioInventario service){
        this.service = service;
    }

    // ==============================
    // Endpoints para Inventario
    // ==============================

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> consultarInventario(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerInventario(id));
    }

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearInventario(@RequestBody InventarioRequestDTO inventario){
        InventarioResponseDTO creado = service.crearInventario(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarInventario(@PathVariable Long id, @RequestBody InventarioRequestDTO inventario) {
        service.actualizarInventario(id, inventario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivarInventario(@PathVariable Long id){
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
    public ResponseEntity<Void> actualizarBodega(@PathVariable Long id, @RequestBody BodegaRequestDTO bodega) {
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

    // ==============================
    // Métodos para Sucursal
    // ==============================

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<SucursalResponseDTO> consultarSucursal(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerSucursal(id));
    }

    @PostMapping("/sucursal")
    public ResponseEntity<SucursalResponseDTO> crearSucursal(@RequestBody SucursalRequestDTO sucursal){
        SucursalResponseDTO creada = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/sucursal/{id}")
    public ResponseEntity<Void> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalRequestDTO sucursal) {
        service.actualizarSucursal(id, sucursal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sucursal/{id}")
    public ResponseEntity<String> desactivarSucursal(@PathVariable Long id){
        service.desactivarSucursal(id);
        return ResponseEntity.status(204).body(String.format("Sucursal %d desactivada correctamente", id));
    }

    @GetMapping("/sucursal")
    public ResponseEntity<List<SucursalResponseDTO>> listarTodas(){
        return ResponseEntity.ok(service.listarSucursales());
    }

    // ==============================
    // Endpoints para Familia
    // ==============================

    @GetMapping("/familia/{id}")
    public ResponseEntity<FamiliaResponseDTO> consultarFamilia(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerFamilia(id));
    }

    @PostMapping("/familia")
    public ResponseEntity<FamiliaResponseDTO> crearFamilia(@RequestBody FamiliaRequestDTO familia){
        FamiliaResponseDTO creada = service.crearFamilia(familia);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/familia/{id}")
    public ResponseEntity<Void> actualizarFamilia(@PathVariable Long id, @RequestBody FamiliaRequestDTO familia) {
        service.actualizarFamilia(id, familia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/familia/{id}")
    public ResponseEntity<String> desactivarFamilia(@PathVariable Long id){
        service.desactivarFamilia(id);
        return ResponseEntity.status(204).body(String.format("Familia %d desactivada correctamente", id));
    }

    @GetMapping("/familia")
    public ResponseEntity<List<FamiliaResponseDTO>> listarFamilias(){
        return ResponseEntity.ok(service.listarFamilias());
    }

    // ==============================
    // Endpoints para SubFamilia
    // ==============================

    @GetMapping("/subfamilia/{id}")
    public ResponseEntity<SubFamiliaResponseDTO> consultarSubFamilia(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerSubFamilia(id));
    }

    @PostMapping("/subfamilia")
    public ResponseEntity<SubFamiliaResponseDTO> crearSubFamilia(@RequestBody SubFamiliaRequestDTO subfamilia){
        SubFamiliaResponseDTO creada = service.crearSubFamilia(subfamilia);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/subfamilia/{id}")
    public ResponseEntity<Void> actualizarSubFamilia(@PathVariable Long id, @RequestBody SubFamiliaRequestDTO subfamilia) {
        service.actualizarSubFamilia(id, subfamilia);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/subfamilia/{id}")
    public ResponseEntity<String> desactivarSubFamilia(@PathVariable Long id){
        service.desactivarSubFamilia(id);
        return ResponseEntity.status(204).body(String.format("SubFamilia %d desactivada correctamente", id));
    }

    @GetMapping("/subfamilia")
    public ResponseEntity<List<SubFamiliaResponseDTO>> listarSubFamilias(){
        return ResponseEntity.ok(service.listarSubFamilias());
    }

    // ==============================
    // Endpoints para Producto
    // ==============================

    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseDTO> consultarProducto(@PathVariable Long id){
        return ResponseEntity.ok(service.obtenerProducto(id));
    }

    @PostMapping("/producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoRequestDTO producto){
        ProductoResponseDTO creado = service.crearProducto(producto);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO producto) {
        service.actualizarProducto(id, producto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<String> desactivarProducto(@PathVariable Long id){
        service.desactivarProducto(id);
        return ResponseEntity.status(204).body(String.format("Producto %d desactivado correctamente", id));
    }

    @GetMapping("/producto")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos(){
        return ResponseEntity.ok(service.listarProductos());
    }
}