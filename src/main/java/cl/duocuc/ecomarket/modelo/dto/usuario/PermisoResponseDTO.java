package cl.duocuc.ecomarket.modelo.dto.usuario;

import cl.duocuc.ecomarket.modelo.dto.RespuestaDTO;
import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.validacion.Requerido;
import jakarta.validation.constraints.NotNull;

public record PermisoResponseDTO(

        @NotNull(message = "El id no puede ser nulo")
        Integer id,

        @Requerido
        String clave,

        String descripcion
) implements RespuestaDTO {

        public static RespuestaDTO fromEntidad(Permiso p){
                return new PermisoResponseDTO(
                        p.getId(),
                        p.getClavePermiso(),
                        p.getDescripcion()
                );
        }

        @Override
        public Number getCodigo() {
                return id;
        }

        @Override
        public String getDescripcion() {
                return descripcion;
        }


}
