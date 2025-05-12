package cl.duocuc.ecomarket.modelo.repository.Producto;

import cl.duocuc.ecomarket.modelo.entity.inventario.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoReponsitory extends JpaRepository<Producto, Integer> {
}
