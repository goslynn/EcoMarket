package cl.duocuc.ecomarket.modelo.mapper.Inventario;


import cl.duocuc.ecomarket.modelo.dto.inventario.Inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;
import cl.duocuc.ecomarket.modelo.entity.inventario.Producto;

public class InventarioMapper {

    // Convierte una entidad Inventario a un DTO de Respuesta
    public static InventarioResponseDTO toDTO(Inventario inventario) {
        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getIdProducto().getNombreProducto(), // Asumiendo que Producto tiene un método getNombreProducto()
                inventario.getStockActual(),
                inventario.getStockMinimo(),
                inventario.getStockMaximo(),
                inventario.getIdBodega(),
                inventario.getActivo()
        );
    }

    public static Inventario toEntity(InventarioRequestDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setIdBodega(dto.idBodega());
        inventario.setStockActual(dto.stockActual());
        inventario.setStockMinimo(dto.stockMinimo());
        inventario.setStockMaximo(dto.stockMaximo());

        // Crear el objeto Producto
        Producto producto = new Producto();
        producto.setId(dto.idProducto());  // Asignar solo el ID
        inventario.setIdProducto(producto);

        inventario.setActivo(true); // Nuevos inventarios son activos por defecto
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

