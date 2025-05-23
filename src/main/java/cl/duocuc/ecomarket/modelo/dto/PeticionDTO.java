package cl.duocuc.ecomarket.modelo.dto;

/**
 * Interfaz para creacion de java records DTO
 * que son una peticion (JSON) requerida por la API
 * para representar una entidad de la base de datos
 * @param <E> Entidad a representar
 */
public interface PeticionDTO<E> {

    /**
     * Convierte el DTO a la entidad que representa
     * @return Entidad correspondiente
     */
    E toEntidad();

}
