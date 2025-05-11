// UsuarioUpdateRequestDTO.java

package cl.duocuc.ecomarket.modelo.dto.usuario;

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
        @Size(min = 1, max = 255) String nombre,
        @Size(min = 1, max = 255) String correo,
        @Size(min = 8, max = 255) String contrasenaHash,
        @Size(min = 8, max = 10) String fechaNacimiento,
        @Size(min = 1, max = 1) String genero,
        Integer idRol,
        @Size(min = 1, max = 25) String telefono,
        @Size(min = 1, max = 20) String rutEmpleado,
        @Size(min = 8, max = 10) String fechaContratacion,
        @Size(min = 1, max = 255) String cargoEmpleado,
        @Size(min = 1, max = 255) String areaEmpleado
) {}
