package cl.duocuc.ecomarket.modelo.repository.Roles;


import cl.duocuc.ecomarket.modelo.entity.usuario.RolesPermiso;
import cl.duocuc.ecomarket.modelo.entity.usuario.RolesPermisoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesPermisosRepository extends JpaRepository<RolesPermiso, RolesPermisoId> {
}
