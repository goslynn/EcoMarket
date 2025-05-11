package cl.duocuc.ecomarket.modelo.mapper;


import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;

public class InventarioMapper {

    // Convierte una entidad Inventario a un DTO de Respuesta
    public static InventarioResponseDTO toDTO(Inventario inventario) {
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getIdProducto().getNombreProducto(), // Asumiendo que Producto tiene un nombre
                inventario.getStockActual(),
                inventario.getStockMaximo(),
                inventario.getStockMaximo(),
                inventario.getIdBodega(),
                inventario.getActivo()
        );
    }

    // Convierte un DTO de Request a una entidad Inventario
    public static Inventario toEntity(InventarioRequestDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setIdBodega(dto.idBodega());
        inventario.setStockActual(dto.stockActual());
        inventario.setStockMinimo(dto.stockMinimo());
        inventario.setStockMaximo(dto.stockMaximo());
        inventario.setActivo(true); // Por defecto, los nuevos inventarios son activos
        return inventario;
    }

    // Convierte de Entidad a DTO de Respuesta
    public static InventarioResponseDTO toResponseDTO(Inventario inventario) {
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getIdProducto().getNombreProducto(),
                inventario.getStockActual(),
                inventario.getStockMinimo(),
                inventario.getStockMaximo(),
                inventario.getIdBodega(),
                inventario.getActivo()
        );
    }
}

