package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.util.validacion.Requerido;

import java.util.List;

public record RolPermisosRequestDTO (

        @Requerido
        String nombre,

        @Requerido
        String descripcion,

        List<Integer> permisos
) {}
