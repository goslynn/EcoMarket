package cl.duocuc.ecomarket.modelo.dto;

/**
 * Interfaz para la creacion de java records DTO
 * que son una respuesta (JSON) de la API
 * representando una entidad de la base de datos
 * @param <E> Entidad a representar
 */
public interface RespuestaDTO<E> {

    /**
     * Obtiene una instancia de este record a raiz
     * de una entidad de la base de datos
     * @param entidad Entidad representada
     * @return conversion a DTO
     */
    RespuestaDTO<E> fromEntidad(E entidad);


}
