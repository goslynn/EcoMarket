// UsuarioUpdateRequestDTO.java

package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.tipodatos.Genero;
import cl.duocuc.ecomarket.util.validacion.FechaDB;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import cl.duocuc.ecomarket.util.validacion.Rut;
import cl.duocuc.ecomarket.util.validacion.Telefono;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *Esta DTO lo cree para que admita los datos de actualizacion no se utiliza la de insert porque no supe como
 * y esta es mi solucion y para poder modicarla en un futuro si se necesita agregar algo mas
 * @param nombre
 * @param correo
 * @param contrasenaHash
 * @param fechaNacimiento
 * @param genero
 * @param idRol
 * @param telefono
 * @param rutEmpleado
 * @param fechaContratacion
 * @param cargoEmpleado
 * @param areaEmpleado
 */
public record UsuarioUpdateRequestDTO(

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
) {}
