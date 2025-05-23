package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;

import java.math.BigDecimal;

public record DetalleVentaDTO (
        Long idProducto,
        Long cantidad,
        BigDecimal precioUnitario
) implements RespuestaDTO<DetalleVenta> {

    @Override
    public  RespuestaDTO<DetalleVenta> fromEntidad(DetalleVenta entidad) {
        return new DetalleVentaDTO(
                entidad.getIdProducto(),
                entidad.getCantidad(),
                entidad.getPrecioUnitario()
        );
    }

}
