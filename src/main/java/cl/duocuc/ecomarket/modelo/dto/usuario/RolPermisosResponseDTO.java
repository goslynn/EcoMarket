package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RolPermisosResponseDTO(

        @NotNull(message = "El id no puede ser nulo")
        Integer id,

        @Requerido
        String nombre,

        @Requerido
        String descripcion,

        List<PermisoResponseDTO> permisos
) {}
