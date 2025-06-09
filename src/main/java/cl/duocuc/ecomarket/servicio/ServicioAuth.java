package cl.duocuc.ecomarket.servicio;

import cl.duocuc.ecomarket.funcional.PermisoFuncional;
import cl.duocuc.ecomarket.modelo.dto.usuario.LoginRequestDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.security.EcomarketJWT;
import cl.duocuc.ecomarket.security.ProveedorJWT;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.exception.ApiException;
import cl.duocuc.ecomarket.util.exception.ErrorDatos;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ServicioAuth {


    private final ServicioUsuarios servicioUsuarios;
    private final ProveedorJWT jwt;

    public ServicioAuth(ServicioUsuarios servicioUsuarios, EcomarketJWT jwt) {
        this.servicioUsuarios = servicioUsuarios;
        this.jwt = jwt;
    }



    /* TODO: Recordar que la generacion de permisos es con 1 unico numero cuantificable
        donde el primer digito se valida con >=, <= y los segundos dos digitos se validan
        con .equals()
     */
    public CodigoDescripcion<Integer, String> login(LoginRequestDTO dto) throws ApiException{
        Usuario u = servicioUsuarios.obtenerUsuario(dto);
        return CodigoDescripcion.of(
                u.getId(),
                jwt.generarToken(u.getId(), u.getRol().getId())
        );
    }

    public PermisoFuncional[] getPermisos(final Usuario usuario) throws ApiException {
        return Arrays.stream(servicioUsuarios.buscarPermisos(usuario))
                     .map(p -> {
                         try {
                             return new PermisoFuncional(p);
                         } catch (ErrorDatos e) {
                             throw new ApiException(500, "Error resolviendo permisos.", e);
                         }
                     })
                     .toArray(PermisoFuncional[]::new);
    }


}
