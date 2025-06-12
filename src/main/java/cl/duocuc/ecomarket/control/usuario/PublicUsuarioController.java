package cl.duocuc.ecomarket.control.usuario;


import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/user")
public class PublicUsuarioController {
    private final ServicioUsuarios service;
    private static final Logger log = LoggerFactory.getLogger(PublicUsuarioController.class);


    public PublicUsuarioController(ServicioUsuarios service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> consultarUsuario(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerUsuario(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody ClienteRequestDTO usuario){
        return ResponseEntity.status(201).body(service.registrar(usuario));
    }

    /**
     * Este es el put que hace la el json insert el update ya nose que mas poner vivan las goticas.
     * eso.
     * @param id
     * @param usuario
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CodigoDescripcion<Integer,String>> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuario) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id){
        service.desactivarUsuario(id);
        return ResponseEntity.status(204).body(String.format("Usuario %d eliminado correctamente", id));
    }

    @PostMapping("/rol")
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolPermisosRequestDTO dto) {
        return ResponseEntity.status(201).body(service.crearRol(dto));
    }

    @GetMapping("/rol/{id}")
    public ResponseEntity<RolDTO> consultarRol(@PathVariable Integer id)  {
        return ResponseEntity.ok(service.obtenerRol(id));
    }

    @GetMapping("/rol")
    public ResponseEntity<List<RolDTO>> consultarRoles() {
        return ResponseEntity.ok(service.obtenerRoles());
    }


    @PutMapping("/rol/{id}")
    public ResponseEntity<CodigoDescripcion<Integer,String>> actualizarRol(@PathVariable Integer id, @Valid @RequestBody RolPermisosRequestDTO rol) {
        return ResponseEntity.ok(service.actualizarRol(id, rol));
    }

    @DeleteMapping("/rol/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Integer id)  {
        service.eliminarRol(id);
        return ResponseEntity.status(204).body(String.format("Rol %d eliminado correctamente", id));
    }

    @GetMapping("/permiso")
    public ResponseEntity<List<PermisoResponseDTO>> consultarPermisos(){
        return ResponseEntity.ok(service.obtenerPermisos());
    }

    @GetMapping("/rol/{id}/permiso")
    public ResponseEntity<RolPermisosResponseDTO> consultarPermisosRol(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerRolPermisos(id));
    }



}