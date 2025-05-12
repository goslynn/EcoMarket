package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BodegaUpdateDTO(
        @NotNull Long idBodega,
        @NotNull Long idSucursal,
        @NotBlank @Size(max = 100) String nombreBodega
){}
