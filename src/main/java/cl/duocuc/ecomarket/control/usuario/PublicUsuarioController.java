package cl.duocuc.ecomarket.control.usuario;


import cl.duocuc.ecomarket.modelo.dto.usuario.*;
import cl.duocuc.ecomarket.modelo.dto.usuario.signup.*;
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
    public ResponseEntity<UsuarioResponseDTO> consultar(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerUsuario(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody ClienteRegistroDTO usuario){
        return ResponseEntity.status(201).body(service.registrar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@Valid @RequestBody UsuarioLoginRequestDTO usuario){
        return null;
    }


    /**
     * Este es el put que hace la el json insert el update ya nose que mas poner vivan las goticas.
     * eso.
     * @param id
     * @param usuario
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<CodigoDescripcion<Integer,String>> actualizar(@PathVariable Integer id, @RequestBody UsuarioUpdateRequestDTO usuario) {
        return ResponseEntity.ok(service.actualizar(id, usuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable Integer id){
        service.desactivarUsuario(id);
        return ResponseEntity.status(204).body(String.format("Usuario %d eliminado correctamente", id));
    }

    @GetMapping("/rol/{id}")
    public ResponseEntity<RolDTO> consultarRol(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerRol(id));
    }

    @GetMapping("/rol")
    public ResponseEntity<List<RolDTO>> consultarRoles(){
        return ResponseEntity.ok(service.obtenerRoles());
    }

    @GetMapping("/permiso")
    public ResponseEntity<List<PermisoResponseDTO>> consultarPermisos(){
        return ResponseEntity.ok(service.obtenerPermisos());
    }

    @GetMapping("/rol/{id}/permiso")
    public ResponseEntity<RolPermisosDTO> consultarPermisosRol(@PathVariable Integer id){
        return ResponseEntity.ok(service.obtenerRolPermisos(id));
    }

}