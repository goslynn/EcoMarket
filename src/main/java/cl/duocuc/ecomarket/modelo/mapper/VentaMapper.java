package cl.duocuc.ecomarket.modelo.mapper;

import cl.duocuc.ecomarket.modelo.dto.venta.DetalleVentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaDTO;
import cl.duocuc.ecomarket.modelo.dto.venta.VentaResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.DetalleVenta;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;

public class VentaMapper {

    public static VentaResponseDTO toResponseDTO(Venta v){
        return new VentaResponseDTO(
                v.getId(),
                v.getIdEstadoVenta(),
                v.getIdTipoDocumento(),
                v.getTotalNeto(),
                v.getTotalIva(),
                v.getTotalBruto(),
                toDTO(v),
                v.getFechaCreacion() == null ? "" : v.getFechaCreacion().toString()
        );
    }

    public static VentaDTO toDTO(Venta v){
        return new VentaDTO(
                v.getIdCliente(),
                v.getIdFormaPago(),
                v.getObservaciones(),
                v.getDetalleVentas().stream()
                        .map(VentaMapper::toDetaleVenta)
                        .toList()
        );
    }

    public static DetalleVentaDTO toDetaleVenta(DetalleVenta v){
        return new DetalleVentaDTO(
                v.getIdProducto().getId(),
                v.getCantidad(),
                v.getPrecioUnitario()
        );
    }
}
