package cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SucursalRequestDTO(
        @Requerido  @Size(max = 100) String nombreSucursal
){}
