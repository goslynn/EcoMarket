package cl.duocuc.ecomarket.modelo.dto.venta;

import java.math.BigDecimal;

public record DetalleVentaDTO (
        Long idProducto,
        Integer cantidad,
        BigDecimal precioUnitario
){}
