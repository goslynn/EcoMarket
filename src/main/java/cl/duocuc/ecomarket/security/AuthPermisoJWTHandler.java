package cl.duocuc.ecomarket.security;

import cl.duocuc.ecomarket.funcional.RequierePermiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioAuth;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthPermisoJWTHandler implements HandlerInterceptor {

    private final ServicioAuth servicio;


    public AuthPermisoJWTHandler(ServicioAuth servicio) {
        this.servicio = servicio;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod metodo) {
            RequierePermiso anotacionPermiso = metodo.getMethodAnnotation(RequierePermiso.class);
            if (anotacionPermiso == null) {
                return true;
            }
            return autorizar(resolverToken(request), anotacionPermiso.value());
        }
        return true;
    }

    private String resolverToken(HttpServletRequest request) {
        return request.getHeader("Authorization") != null
                ? request.getHeader("Authorization").replace("Bearer ", "")
                : null;
    }

    private boolean autorizar(String token, TipoPermiso requiere) throws ApiException {
        if (token == null || token.isBlank()) {
            throw new ApiException(401, "Token de autorización no encontrado o mal formado. Asegúrese de incluir el prefijo 'Bearer ' en el encabezado Authorization.");
        }
        if (!servicio.isAutorizado(token, requiere)) {
            Usuario u = servicio.getUsuario(token);
            throw new ApiException(
                    401, String.format("Operacion no autorizada, no posee los permisos necesarios para realizar esta accion. ( USUARIO: {%d-%s} , PETICION: %s ) ",
                    u.getId(), u.getNombreUsuario(), requiere.toString())
            );
        }
        return true;
    }

}
