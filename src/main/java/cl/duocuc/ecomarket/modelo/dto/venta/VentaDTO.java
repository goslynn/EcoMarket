package cl.duocuc.ecomarket.modelo.dto.venta;

import java.util.List;

public record VentaDTO (
        Integer idCliente,
        Integer idFormaPago,
        String observaciones,
        List<DetalleVentaDTO> items
){}
