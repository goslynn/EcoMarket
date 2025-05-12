package cl.duocuc.ecomarket.modelo.dto.inventario.Bodega;

public record BodegaResponseDTO(
        Long id,
        String nombreBodega,
        Boolean activa,
        Long idSucursal
) {}
