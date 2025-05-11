package cl.duocuc.ecomarket.modelo.dto.usuario;

import java.util.List;

public record RolPermisosDTO (
        Integer id,
        String nombre,
        String descripcion,
        List<PermisoResponseDTO> permisos
) {}
