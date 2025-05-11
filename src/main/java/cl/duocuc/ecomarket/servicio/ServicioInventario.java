package cl.duocuc.ecomarket.servicio;


import cl.duocuc.ecomarket.modelo.dto.inventario.InventarioResponseDTO;
import cl.duocuc.ecomarket.modelo.entity.inventario.Inventario;
import cl.duocuc.ecomarket.modelo.mapper.InventarioMapper;
import cl.duocuc.ecomarket.modelo.repository.InventarioRepository;
import cl.duocuc.ecomarket.util.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioInventario {

    private final InventarioRepository inventarioRepo;

    @Autowired
    public ServicioInventario(InventarioRepository inventarioRepo) {
        this.inventarioRepo = inventarioRepo;
    }

    public InventarioResponseDTO obtenerInventario(Long id) throws ApiException {
        return inventarioRepo.findById(id)
                .filter(Inventario::getActivo)
                .map(InventarioMapper::toResponseDTO)
                .orElseThrow(() -> new ApiException(404, String.format("El inventario con ID %d no existe", id)));
    }




}


//    @Transactional
//    public InventarioResponseDTO crearInventario(InventarioRequestDTO inventario) throws ApiException {
//        return persistencia.agregarInventario(inventario);
//    }
//
//    @Transactional
//    public void actualizarInventario(Long id, InventarioRequestDTO inventario) throws ApiException {
//        if (id == null || id <= 0) {
//            throw new ApiException(400, "El ID del inventario no es válido");
//        }
//        persistencia.actualizarInventario(id, inventario);
//    }
//
//    @Transactional
//    public void desactivarInventario(Long id) throws ApiException {
//        if (id == null || id <= 0) {
//            throw new ApiException(400, "El ID del inventario no es válido");
//        }
//        persistencia.desactivarInventario(id);
//    }
//
//    public List<InventarioResponseDTO> listarInventarios() throws ApiException {
//        List<InventarioResponseDTO> inventarios = persistencia.listarInventarios();
//        if (inventarios.isEmpty()) {
//            throw new ApiException(404, "No existen inventarios");
//        }
//        return inventarios;
//    }



