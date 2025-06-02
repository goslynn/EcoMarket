package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;


public record LoginRequestDTO(
        String nombreUsuario,
        String contrasena
) implements PeticionDTO<Usuario> {

    @Override
    public Usuario toEntidad() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        String passw;
        if (!ServicioUsuarios.encriptador.encriptado(contrasena)) {
            passw  = ServicioUsuarios.encriptador.encriptar(contrasena);
        } else {
            passw = contrasena;
        }
        usuario.setContrasenaHash(passw);
        return usuario;
    }
}
