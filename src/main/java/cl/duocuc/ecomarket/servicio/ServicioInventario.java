package cl.duocuc.ecomarket.servicio;


import cl.duocuc.ecomarket.modelo.dto.inventario.Bodega.BodegaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Bodega.BodegaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Bodega.BodegaUpdateDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Familia.FamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Familia.FamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Inventario.InventarioRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamilia.SubFamiliaRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.SubFamilia.SubFamiliaResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal.SucursalRequestDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal.SucursalResponseDTO;
import cl.duocuc.ecomarket.modelo.dto.inventario.Sucursal.SucursalUpdateDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.*;
import cl.duocuc.ecomarket.modelo.mapper.Bodega.BodegaMapper;
import cl.duocuc.ecomarket.modelo.mapper.Familia.FamiliaMapper;
import cl.duocuc.ecomarket.modelo.mapper.Inventario.InventarioMapper;
import cl.duocuc.ecomarket.modelo.mapper.SubFamilia.SubFamiliaMapper;
import cl.duocuc.ecomarket.modelo.mapper.Sucursal.SucursalMapper;
import cl.duocuc.ecomarket.modelo.repository.Bodega.BodegaRepository;
import cl.duocuc.ecomarket.modelo.repository.Familia.FamiliaRepository;
import cl.duocuc.ecomarket.modelo.repository.Inventario.InventarioRepository;
import cl.duocuc.ecomarket.modelo.repository.SubFamilia.SubFamiliaRepository;
import cl.duocuc.ecomarket.modelo.repository.Sucursal.SucursalRepository;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicioInventario {

    private final InventarioRepository inventarioRepo;
    private final BodegaRepository bodegaRepo;
    private final SucursalRepository sucursalRepo;
    private final FamiliaRepository familiaRepo;
    private final SubFamiliaRepository subFamiliaRepo;
    //private final  bodegaRepo;

    public ServicioInventario(InventarioRepository inventarioRepo, BodegaRepository bodegaRepo, SucursalRepository sucursalRepo, FamiliaRepository familiaRepo, SubFamiliaRepository subFamiliaRepo) {
        this.inventarioRepo = inventarioRepo;
        this.bodegaRepo = bodegaRepo;
        this.sucursalRepo = sucursalRepo;
        this.familiaRepo = familiaRepo;
        this.subFamiliaRepo = subFamiliaRepo;
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

    public void actualizarBodega(Long id, BodegaUpdateDTO dto) throws ApiException {
        Bodega existente = bodegaRepo.findById(dto.idBodega())
                .filter(Bodega::getActiva)
                .orElseThrow(() -> new ApiException(404, String.format("La bodega con ID %d no existe", dto.idBodega())));

        // Actualiza el nombre de la bodega
        existente.setNombreBodega(dto.nombreBodega());

        bodegaRepo.save(existente);
    }


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


    // ==============================
    // Métodos para Sucursal
    // ==============================

    public SucursalResponseDTO obtenerSucursal(Long id) throws ApiException {
        return sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .map(SucursalMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));
    }

    @Transactional
    public SucursalResponseDTO crearSucursal(SucursalRequestDTO dto) {
        Sucursal nuevaSucursal = SucursalMapper.toEntity(dto);
        Sucursal guardada = sucursalRepo.save(nuevaSucursal);
        return SucursalMapper.toResponseDTO(guardada);
    }

    @Transactional
    public void actualizarSucursal(Long id, SucursalUpdateDTO dto) throws ApiException {
        Sucursal existente = sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));

        existente.setNombreSucursal(dto.nombreSucursal());
        sucursalRepo.save(existente);
    }

    @Transactional
    public void desactivarSucursal(Long id) throws ApiException {
        Sucursal existente = sucursalRepo.findById(id)
                .filter(Sucursal::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La sucursal con ID %d no existe", id)));

        existente.setActivo(false);
        sucursalRepo.save(existente);
    }

    public List<SucursalResponseDTO> listarSucursales() throws ApiException {
        return sucursalRepo.findAll().stream()
                .filter(Sucursal::getActivo)
                .map(SucursalMapper::toResponseDTO)
                .toList();
    }

    // ==============================
    // Métodos para Familia
    // ==============================

    // ==============================
    // Métodos para Familia
    // ==============================

    public FamiliaResponseDTO obtenerFamilia(Long id) throws ApiException {
        return familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .map(FamiliaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));
    }

    @Transactional
    public FamiliaResponseDTO crearFamilia(FamiliaRequestDTO dto) {
        Familia nuevaFamilia = FamiliaMapper.toEntity(dto);
        Familia guardada = familiaRepo.save(nuevaFamilia);
        return FamiliaMapper.toResponseDTO(guardada);
    }

    @Transactional
    public void actualizarFamilia(Long id, FamiliaRequestDTO dto) throws ApiException {
        Familia existente = familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));

        existente.setNombreFamilia(dto.nombre_familia());
        existente.setDescripcion(dto.descripcion());
        familiaRepo.save(existente);
    }

    @Transactional
    public void desactivarFamilia(Long id) throws ApiException {
        Familia existente = familiaRepo.findById(id)
                .filter(Familia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La familia con ID %d no existe", id)));

        existente.setActivo(false);
        familiaRepo.save(existente);
    }

    public List<FamiliaResponseDTO> listarFamilias() throws ApiException {
        return familiaRepo.findAll().stream()
                .filter(Familia::getActivo)
                .map(FamiliaMapper::toResponseDTO)
                .toList();
    }

    // ==============================
    // Métodos para SubFamilia
    // ==============================

    public SubFamiliaResponseDTO obtenerSubFamilia(Long id) throws ApiException {
        return subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .map(SubFamiliaMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));
    }

    @Transactional
    public SubFamiliaResponseDTO crearSubFamilia(SubFamiliaRequestDTO dto) throws ApiException {
        Subfamilia subfamilia = SubFamiliaMapper.toEntity(dto);
        Subfamilia guardada = subFamiliaRepo.save(subfamilia);
        return SubFamiliaMapper.toResponseDTO(guardada);
    }

    @Transactional
    public void actualizarSubFamilia(Long id, SubFamiliaRequestDTO dto) throws ApiException {
        Subfamilia existente = subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));

        existente.setNombreSubfamilia(dto.nombre_subfamilia());
        existente.setDescripcion(dto.descripcion());
        subFamiliaRepo.save(existente);
    }

    @Transactional
    public void desactivarSubFamilia(Long id) throws ApiException {
        Subfamilia existente = subFamiliaRepo.findById(id)
                .filter(Subfamilia::getActivo)
                .orElseThrow(() -> new ApiException(404, String.format("La subfamilia con ID %d no existe", id)));

        existente.setActivo(false);
        subFamiliaRepo.save(existente);
    }

    public List<SubFamiliaResponseDTO> listarSubFamilias() throws ApiException {
        return subFamiliaRepo.findAll().stream()
                .filter(Subfamilia::getActivo)
                .map(SubFamiliaMapper::toResponseDTO)
                .toList();
    }


}

