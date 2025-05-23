package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public record VentaResponseDTO (
        Integer idVenta,
        Integer idEstadoVenta,
        Integer idTipoDocumento,
        BigDecimal subTotal,
        BigDecimal totalIva,
        BigDecimal total,
        PeticionVentaDTO venta,
        String fechaCreacion
)implements RespuestaDTO<Venta> {

    @Override
    public RespuestaDTO<Venta> fromEntidad(Venta entidad) {
        PeticionVentaDTO desglose = new PeticionVentaDTO(
                entidad.getIdCliente(),
                entidad.getIdFormaPago(),
                entidad.getObservaciones(),
                entidad.getDetalleVentas().stream()
                       .map(dv -> new DetalleVentaDTO(null, null, null).fromEntidad(dv))
                       .collect(Collectors.toList())
        );
        return new VentaResponseDTO(
                entidad.getId(),
                entidad.getIdEstadoVenta(),
                entidad.getIdTipoDocumento(),
                entidad.getTotalNeto(),
                entidad.getTotalIva(),
                entidad.getTotalBruto(),
                ,
                entidad.getFechaCreacion()
        );
    }
}
