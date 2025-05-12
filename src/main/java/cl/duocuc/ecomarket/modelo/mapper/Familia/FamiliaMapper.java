package cl.duocuc.ecomarket.modelo.mapper.Familia;

import cl.duocuc.ecomarket.modelo.dto.inventario.Familia.FamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Familia.FamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Familia;

public class FamiliaMapper {

    public static FamiliaResponseDTO toResponseDTO(Familia familia) {
        return new FamiliaResponseDTO(
                familia.getId(),
                familia.getNombreFamilia(),
                familia.getDescripcion()
        );
    }


    public static Familia toEntity(FamiliaRequestDTO dto) {
        Familia familia = new Familia();
        familia.setNombreFamilia(dto.nombre_familia());
        familia.setDescripcion(dto.descripcion());
        familia.setActivo(true);
        return familia;
    }

}
