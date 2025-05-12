package cl.duocuc.ecomarket.modelo.dto.inventario;

import jakarta.validation.constraints.NotNull;

public record SucursalRequestDTO(
        @NotNull Long idSucursal,
        @NotNull String nombreSucursal
){}
