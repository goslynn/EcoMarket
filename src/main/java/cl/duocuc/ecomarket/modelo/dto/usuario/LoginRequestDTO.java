package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.PeticionEncriptableDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;


public record LoginRequestDTO(
        String nombreUsuario,

        //TODO: Validacion contraseña...
        String contrasena
) implements PeticionEncriptableDTO<Usuario> {

    @Override
    public Usuario toEntidad(Encriptador<String> enc) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        String passw;
        if (!enc.encriptado(contrasena)) {
            passw  = enc.encriptar(contrasena);
        } else {
            passw = contrasena;
        }
        usuario.setContrasenaHash(passw);
        return usuario;
    }
}
