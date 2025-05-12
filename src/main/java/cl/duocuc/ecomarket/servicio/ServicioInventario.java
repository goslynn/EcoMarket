package cl.duocuc.ecomarket.servicio;


import cl.duocuc.ecomarket.modelo.dto.inventario.*;
import cl.duocuc.ecomarket.modelo.entity.inventario.Bodega;
import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;
import cl.duocuc.ecomarket.modelo.entity.inventario.Sucursal;
import cl.duocuc.ecomarket.modelo.mapper.BodegaMapper;
import cl.duocuc.ecomarket.modelo.mapper.InventarioMapper;
import cl.duocuc.ecomarket.modelo.repository.BodegaRepository;
import cl.duocuc.ecomarket.modelo.repository.InventarioRepository;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioInventario {

    private final InventarioRepository inventarioRepo;
    private final BodegaRepository bodegaRepo;
    //private final  bodegaRepo;

    public ServicioInventario(InventarioRepository inventarioRepo, BodegaRepository bodegaRepo) {
        this.inventarioRepo = inventarioRepo;
        this.bodegaRepo = bodegaRepo;
    }


    // ==============================
    // Métodos para Inventario
    // ==============================

    public InventarioResponseDTO obtenerInventario(Long id) throws ApiException {
        return inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .map(InventarioMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));
    }


    public InventarioResponseDTO crearInventario(InventarioRequestDTO dto) {
        Inventario nuevoInventario = InventarioMapper.toEntity(dto);
        Inventario guardado = inventarioRepo.save(nuevoInventario);
        return InventarioMapper.toResponseDTO(guardado);
    }

    public void actualizarInventario(Long id, InventarioRequestDTO dto) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        inventarioRepo.save(existente);
    }

    public void desactivarInventario(Long id) throws ApiException {
        Inventario existente = inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));

        // Cambia el estado a inactivo
        existente.setActivo(false);
        inventarioRepo.save(existente);
    }

         // ==============================
        // Métodos para Bodega
        // ==============================

    public BodegaResponseDTO obtenerBodega(Long id) throws ApiException {
        return bodegaRepo.findById(id)
                .filter(Bodega::getActiva)
                .map(BodegaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", id)));
    }

    public BodegaResponseDTO crearBodega(BodegaRequestDTO dto) {
        Bodega nuevaBodega = BodegaMapper.toEntity(dto);
        Bodega guardada = bodegaRepo.save(nuevaBodega);
        return BodegaMapper.toResponseDTO(guardada);
    }

    @Transactional
    public void actualizarBodega(Long id, BodegaUpdateDTO dto) throws ApiException {
        Bodega existente = bodegaRepo.findById(dto.idBodega())
                .filter(Bodega::getActiva)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", dto.idBodega())));

        // Actualiza el nombre de la bodega
        existente.setNombreBodega(dto.nombreBodega());

//        // Verifica que la sucursal exista
//        Sucursal sucursal = sucursalRepo.findById(dto.idSucursal())
//                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", dto.idSucursal())));

        // Asigna la nueva sucursal
//        existente.setIdSucursal(sucursal);

        bodegaRepo.save(existente);
    }


    @Transactional
    public void desactivarBodega(Long id) throws ApiException {
        Bodega existente = bodegaRepo.findById(id)
                .filter(Bodega::getActiva)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", id)));
        bodegaRepo.save(existente);
    }

    public List<BodegaResponseDTO> listarBodegas() throws ApiException {
        return bodegaRepo.findAll().stream()
                .filter(Bodega::getActiva)
                .map(BodegaMapper::toResponseDTO)
                .toList();
    }
}

