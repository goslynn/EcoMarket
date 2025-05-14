package cl.duocuc.ecomarket.modelo.dto.inventario.Producto;

import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long idProducto,
        String CodigoProducto,
        String NombreProducto,
        String Descripcion,
        BigDecimal Precio,
        Long idSubFamilia,
        Boolean Activo
){}
