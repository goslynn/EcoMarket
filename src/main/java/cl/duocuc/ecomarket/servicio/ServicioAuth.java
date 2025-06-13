package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.PermisoFuncional;
import cl.duocuc.ecomarket.modelo.dto.usuario.LoginRequestDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.security.EcomarketJWT;
import cl.duocuc.ecomarket.security.ProveedorJWT;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ServicioAuth {


    private final ServicioUsuarios servicioUsuarios;
    private final ProveedorJWT jwt;
    private final Encriptador<String> encriptador;

    public ServicioAuth(ServicioUsuarios servicioUsuarios, EcomarketJWT jwt, Encriptador<String> encriptador) {
        this.servicioUsuarios = servicioUsuarios;
        this.jwt = jwt;
        this.encriptador = encriptador;
    }


    public CodigoDescripcion<Integer, String> login(LoginRequestDTO dto) throws ApiException{
        Usuario u = servicioUsuarios.getUserRepo().findByCorreoUsuario(dto.correo())
                .filter(Usuario::getActivo)
                .orElseThrow(() -> new ApiException(404, "Usuario no encontrado con el correo proporcionado: " + dto.correo()));

        if (!(encriptador.desencriptar(u.getContrasenaHash())).equals(dto.contrasena())) {
            throw new ApiException(401, "Contrasena incorrecta!");
        }

        return CodigoDescripcion.of(
                u.getId(),
                jwt.generarToken(u.getId(), u.getRol().getId())
        );
    }

    public Usuario getUsuario(String token) throws ApiException{
        Integer id = jwt.getIdUsuario(token);
        return servicioUsuarios.getUserRepo().findById(id)
                               .filter(Usuario::getActivo)
                               .orElseThrow(() -> new ApiException(404, String.format("el usuario con id {%d} no existe", id)));
    }

    private List<Permiso>  getPermisos(String token) throws ApiException {
        return servicioUsuarios.buscarPermisos(((Long) jwt.getClaim(token, "rol_id")).intValue());
    }

    public boolean isAutorizado(String token, int tipo) {
        return isAutorizado(token, TipoPermiso.valueOf(tipo));
    }

    public boolean isAutorizado(String token, TipoPermiso requiere) {
        return PermisoFuncional.buscarCandidatos(getPermisos(token), requiere)
                               .map(permisos -> (permisos).stream()
                                                      .anyMatch(p -> PermisoFuncional.esSuficiente(p, requiere)))
                               .orElse(false);
    }




}
