package cl.duocuc.ecomarket.modelo.repository.Bodega;

import cl.duocuc.ecomarket.modelo.entity.inventario.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {

}
