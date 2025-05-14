package cl.duocuc.ecomarket.modelo.dto.inventario.Bodega;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BodegaRequestDTO(
        @Requerido String nombreBodega,
        @NotNull Long idSucursal
) {}
