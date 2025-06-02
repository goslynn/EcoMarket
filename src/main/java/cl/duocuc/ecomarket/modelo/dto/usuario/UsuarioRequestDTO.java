package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Rol;
import cl.duocuc.ecomarket.modelo.entity.usuario.Usuario;
import cl.duocuc.ecomarket.servicio.ServicioUsuarios;
import cl.duocuc.ecomarket.tipodatos.Genero;
import cl.duocuc.ecomarket.util.encriptacion.Encriptador;
import cl.duocuc.ecomarket.util.validacion.FechaDB;
import cl.duocuc.ecomarket.util.validacion.Rut;
import cl.duocuc.ecomarket.util.validacion.Telefono;

public record UsuarioRequestDTO(

        String nombre,

        String correo,

        String contrasenaHash,

        @FechaDB
        String fechaNacimiento,

        Genero genero,

        Integer idRol,

        @Telefono
        String telefono,

        @Rut
        String rutEmpleado,

        @FechaDB
        String fechaContratacion,

        String cargoEmpleado,

        String areaEmpleado
) implements PeticionDTO<Usuario> {

        @Override
        public Usuario toEntidad() {
                Usuario u = new Usuario();
                u.setNombreUsuario(nombre);
                u.setCorreoUsuario(correo);
                Encriptador<String> enc = ServicioUsuarios.encriptador;
                String passw;
                if (!enc.encriptado(contrasenaHash)) {
                        passw = enc.encriptar(contrasenaHash);
                } else {
                        passw = contrasenaHash;
                }
                u.setContrasenaHash(passw);
                u.setGenero(String.valueOf(genero.toChar()));
                u.setFechaNacimiento(fechaNacimiento);
                Rol r = new Rol();
                r.setId(idRol);
                u.setRol(r);
                return u;
        }

}
