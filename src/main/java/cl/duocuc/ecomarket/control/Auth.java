package cl.duocuc.ecomarket.control;

import cl.duocuc.ecomarket.modelo.dto.usuario.LoginRequestDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioAuth;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Auth.PATH)
public class Auth {

    protected static final String PATH = "/api/v1/public/auth";
    private final ServicioAuth auth;


    protected Auth (ServicioAuth authService) {
        this.auth = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<CodigoDescripcion<Integer, String>> login(@RequestBody LoginRequestDTO dto){
        return ResponseEntity.ok(auth.login(dto));
    }

    protected void autorizar(String token, TipoPermiso requiere) throws ApiException {
        if (!auth.isAutorizado(token, requiere)) {
            Usuario u = auth.getUsuario(token);
            throw new ApiException(
                    401, String.format("Operacion no autorizada, no posee los permisos necesarios para realizar esta accion. ( USUARIO: {%d-%s} , PETICION: %s ) ",
                    u.getId(), u.getNombreUsuario(), requiere.toString())
            );
        }
    }


}
