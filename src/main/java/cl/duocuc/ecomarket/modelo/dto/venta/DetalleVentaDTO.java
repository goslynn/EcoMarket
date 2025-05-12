package cl.duocuc.ecomarket.modelo.dto.venta;

import java.math.BigDecimal;

public record DetalleVentaDTO (
        Integer idProducto,
        Integer cantidad,
        BigDecimal precioUnitario
){}
