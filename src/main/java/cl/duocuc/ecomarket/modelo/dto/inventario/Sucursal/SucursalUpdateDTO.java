package cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SucursalUpdateDTO(
        @NotNull Long IdSucursal,
        @NotBlank @Size(max = 100) String nombreSucursal
) {}
