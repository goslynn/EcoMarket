package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

public record PermisoResponseDTO(

        @NotNull(message = "El id no puede ser nulo")
        Integer id,

        @Requerido
        String clave,

        String descripcion
) {}
