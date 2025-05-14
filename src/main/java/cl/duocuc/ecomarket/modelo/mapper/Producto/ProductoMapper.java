package cl.duocuc.ecomarket.modelo.mapper.Producto;

import cl.duocuc.ecomarket.modelo.dto.inventario.Producto.ProductoRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Producto.ProductoResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Familia;
import cl.duocuc.ecomarket.modelo.entity.inventario.Producto;
import cl.duocuc.ecomarket.modelo.entity.inventario.Subfamilia;

public class ProductoMapper {

    public static ProductoResponseDTO toProductoResponseDTO(Producto p) {
        return new ProductoResponseDTO(
                p.getId(),
                p.getCodigoProducto(),
                p.getNombreProducto(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getIdSubfamilia().getId(),
                p.getActivo()
        );
    }

    public static Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();

        // Crear subfamilia usando solo el ID
        Subfamilia subfamilia = new Subfamilia();
        subfamilia.setId(dto.idSubFamilia());

        producto.setNombreProducto(dto.NombreProducto());
        producto.setCodigoProducto(dto.CodigoProducto());
        producto.setDescripcion(dto.Descripcion());
        producto.setPrecio(dto.Precio());
        producto.setIdSubfamilia(subfamilia);
        producto.setActivo(true);

        return producto;
    }

}
