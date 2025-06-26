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

import cl.duocuc.ecomarket.modelo.dto.usuario.UsuarioResponseDTO;
import cl.duocuc.ecomarket.servicio.ServicioInventario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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



    // =========================
// Swagger para Crear Inventario
// =========================
    @Operation(
            summary = "Crear nuevo inventario",
            description = "Permite registrar un nuevo inventario en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del inventario a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InventarioRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Inventario creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = InventarioResponseDTO.class)
            )
    )
    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearInventario(@RequestBody InventarioRequestDTO inventario){
        InventarioResponseDTO creado = service.crearInventario(inventario);
        return ResponseEntity.status(201).body(creado);
    }

    // =========================
// Swagger para Crear Bodega
// =========================
    @Operation(
            summary = "Crear nueva bodega",
            description = "Permite registrar una nueva bodega en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la bodega a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BodegaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Bodega creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = BodegaResponseDTO.class)
            )
    )
    @PostMapping("/bodega")
    public ResponseEntity<BodegaResponseDTO> crearBodega(@RequestBody BodegaRequestDTO bodega){
        BodegaResponseDTO creada = service.crearBodega(bodega);
        return ResponseEntity.status(201).body(creada);
    }

    // =========================
// Swagger para Crear Sucursal
// =========================
    @Operation(
            summary = "Crear nueva sucursal",
            description = "Permite registrar una nueva sucursal en el sistema con los datos proporcionados."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la sucursal a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SucursalRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Sucursal creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SucursalResponseDTO.class)
            )
    )
    @PostMapping("/sucursal")
    public ResponseEntity<SucursalResponseDTO> crearSucursal(@RequestBody SucursalRequestDTO sucursal){
        SucursalResponseDTO creada = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(creada);
    }

    // =========================
// Swagger para Crear Familia
// =========================
    @Operation(
            summary = "Crear nueva familia",
            description = "Permite registrar una nueva familia de productos en el sistema."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la familia a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FamiliaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Familia creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = FamiliaResponseDTO.class)
            )
    )
    @PostMapping("/familia")
    public ResponseEntity<FamiliaResponseDTO> crearFamilia(@RequestBody FamiliaRequestDTO familia){
        FamiliaResponseDTO creada = service.crearFamilia(familia);
        return ResponseEntity.status(201).body(creada);
    }

    // =========================
// Swagger para Crear SubFamilia
// =========================
    @Operation(
            summary = "Crear nueva subfamilia",
            description = "Permite registrar una nueva subfamilia asociada a una familia existente."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos de la subfamilia a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SubFamiliaRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Subfamilia creada exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SubFamiliaResponseDTO.class)
            )
    )
    @PostMapping("/subfamilia")
    public ResponseEntity<SubFamiliaResponseDTO> crearSubFamilia(@RequestBody SubFamiliaRequestDTO subfamilia){
        SubFamiliaResponseDTO creada = service.crearSubFamilia(subfamilia);
        return ResponseEntity.status(201).body(creada);
    }

    // =========================
// Swagger para Crear Producto
// =========================
    @Operation(
            summary = "Crear nuevo producto",
            description = "Permite registrar un nuevo producto en el sistema."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del producto a registrar",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProductoRequestDTO.class)
            )
    )
    @ApiResponse(
            responseCode = "201",
            description = "Producto creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProductoResponseDTO.class)
            )
    )
    @PostMapping("/producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoRequestDTO producto){
        ProductoResponseDTO creado = service.crearProducto(producto);
        return ResponseEntity.status(201).body(creado);
    }












//
//    @Operation(
//            summary = "Consultar usuario por su ID",
//            description = "Permite consultar un usuario específico por su ID. Devuelve los detalles del usuario si existe."
//    )
//    @Parameter(
//            name = "id",
//            description = "ID del usuario a consultar",
//            required = true,
//            example = "1"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Usuario encontrado",
//            content = @Content(
//                    mediaType = "application/json",
//                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UsuarioResponseDTO.class)
//            )
//    )
//    @ApiResponse(
//            responseCode = "404",
//            description = "Usuario no encontrado",
//            content = @Content()
//    )
//    @GetMapping("/{id}")
//    public ResponseEntity<UsuarioResponseDTO> consultarUsuario(@PathVariable Integer id){
//        return null;
//    }

}