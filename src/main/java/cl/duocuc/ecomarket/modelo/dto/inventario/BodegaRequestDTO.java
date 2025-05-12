package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BodegaRequestDTO(
            @NotBlank String nombreBodega,
            @NotNull Long idSucursal
    ) {}
