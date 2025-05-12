package cl.duocuc.ecomarket.servicio;


import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;
import cl.duocuc.ecomarket.modelo.mapper.InventarioMapper;
import cl.duocuc.ecomarket.modelo.repository.InventarioRepository;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioInventario {

    private final InventarioRepository inventarioRepo;

    public ServicioInventario(InventarioRepository inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }

    public InventarioResponseDTO obtenerInventario(Long id) throws ApiException {
        return inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .map(InventarioMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));
    }


    @Transactional
    public InventarioResponseDTO crearInventario(InventarioRequestDTO dto) {
        Inventario nuevoInventario = InventarioMapper.toEntity(dto);
        Inventario guardado = inventarioRepo.save(nuevoInventario);
        return InventarioMapper.toResponseDTO(guardado);
    }

    @Transactional
    public void actualizarInventario(Long id, InventarioRequestDTO dto) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        // Actualiza solo los campos que no son nulos
        if (dto.idBodega() != null) existente.setIdBodega(dto.idBodega());
        if (dto.stockActual() != null) existente.setStockActual(dto.stockActual());
        if (dto.stockMinimo() != null) existente.setStockMinimo(dto.stockMinimo());
        if (dto.stockMaximo() != null) existente.setStockMaximo(dto.stockMaximo());

        inventarioRepo.save(existente);
    }

    @Transactional
    public void desactivarInventario(Long id) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        // Cambia el estado a inactivo
        existente.setActivo(false);
        inventarioRepo.save(existente);
    }

}


