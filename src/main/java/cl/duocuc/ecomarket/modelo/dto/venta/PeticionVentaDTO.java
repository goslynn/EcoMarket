package cl.duocuc.ecomarket.modelo.dto.venta;

import cl.duocuc.ecomarket.modelo.dto.PeticionDTO;
import cl.duocuc.ecomarket.modelo.entity.venta.Venta;

import java.util.List;

public record PeticionVentaDTO (
        Integer idCliente,
        Integer idFormaPago,
        String observaciones,
        List<DetalleVentaDTO> items
) implements PeticionDTO<Venta> {

    @Override
    public Venta toEntidad() {
        return null;
    }

}
