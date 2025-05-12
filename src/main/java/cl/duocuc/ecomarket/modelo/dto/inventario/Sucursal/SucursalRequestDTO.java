package cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SucursalRequestDTO(
        @NotBlank @Size(max = 100) String nombreSucursal
){}
