package cl.duocuc.ecomarket.modelo.dto.venta;

import java.math.BigDecimal;

public record VentaResponseDTO (
        Integer idVenta,
        Integer idEstadoVenta,
        Integer idTipoDocumento,
        BigDecimal subTotal,
        BigDecimal totalIva,
        BigDecimal total,
        VentaDTO venta,
        String fechaCreacion
){}
