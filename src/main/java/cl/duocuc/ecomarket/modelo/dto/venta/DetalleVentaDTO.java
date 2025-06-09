package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;

import java.math.BigDecimal;

public record DetalleVentaDTO(
        Long idProducto,
        Long cantidad,
        BigDecimal precioUnitario
) implements RespuestaDTO, PeticionDTO<DetalleVenta> {

    public static DetalleVentaDTO fromEntidad(DetalleVenta entidad) {
        return new DetalleVentaDTO(
                entidad.getIdProducto(),
                entidad.getCantidad(),
                entidad.getPrecioUnitario()
        );
    }

    @Override
    public DetalleVenta toEntidad() {
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setIdProducto(idProducto);
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setPrecioUnitario(precioUnitario);
        return detalleVenta;
    }

    @Override
    public Long getCodigo() {
        return idProducto;
    }

    @Override
    public String getDescripcion() {
        return "";
    }

}
