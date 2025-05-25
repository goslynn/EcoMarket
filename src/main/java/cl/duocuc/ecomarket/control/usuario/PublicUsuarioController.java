package cl.duocuc.ecomarket.control.usuario;


import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.modelo.dto.usuario.signup.*;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.exception.ApiException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/user")
public class PublicUsuarioController {
    private final ServicioUsuarios service;
    private final ServicioUsuarios servicio;

    private static final Logger log = LoggerFactory.getLogger(PublicUsuarioController.class);
    public PublicUsuarioController(ServicioUsuarios service){
        this.service = service;
        this.servicio = service;
    }

//---------------------------------//
// Roles nuevos
//---------------------------------//

    @PostMapping("/rol")
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolPermisosRequestDTO dto) throws ApiException {
        return ResponseEntity.status(201).body(servicio.crearRol(dto));
    }

    @GetMapping("/rol")
    public ResponseEntity<List<RolDTO>> listarRoles() throws ApiException {
        return ResponseEntity.ok(servicio.obtenerTodosLosRoles());
    }

    @GetMapping("/rol/{id}")
    public ResponseEntity<RolPermisosResponseDTO> consultarRol(@PathVariable Integer id) throws ApiException {
        return ResponseEntity.ok(servicio.obtenerRolConPermisos(id));
    }

    @PutMapping("/rol/{id}")
    public ResponseEntity<String> actualizarRol(@PathVariable Integer id,
                                                @Valid @RequestBody RolPermisosRequestDTO dto) throws ApiException {
        servicio.actualizarRol(id, dto);
        return ResponseEntity.ok(String.format("Rol %d actualizado correctamente", id));
    }

    @DeleteMapping("/rol/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Integer id) throws ApiException {
        servicio.eliminarRol(id);
        return ResponseEntity.status(204).body(String.format("Rol %d eliminado correctamente", id));
    }

    @PostMapping("/rol/{idRol}/permisos/{idPermiso}")
    public ResponseEntity<String> agregarPermisoARol(@PathVariable Integer idRol,
                                                     @PathVariable Integer idPermiso) throws ApiException {
        servicio.agregarPermisoARol(idRol, idPermiso);
        return ResponseEntity.ok(String.format("Permiso %d agregado al rol %d", idPermiso, idRol));
    }

    @DeleteMapping("/rol/{idRol}/permisos/{idPermiso}")
    public ResponseEntity<String> quitarPermisoDeRol(@PathVariable Integer idRol,
                                                     @PathVariable Integer idPermiso) throws ApiException {
        servicio.quitarPermisoARol(idRol, idPermiso);
        return ResponseEntity.ok(String.format("Permiso %d removido del rol %d", idPermiso, idRol));
    }

}
