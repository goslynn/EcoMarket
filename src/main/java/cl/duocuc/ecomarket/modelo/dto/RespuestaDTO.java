package cl.duocuc.ecomarket.modelo.dto;

import cl.duocuc.ecomarket.util.CodigoDescripcion;

/**
 * Interfaz para la creacion de java records DTO
 * que son una respuesta (JSON) de la API
 * representando una entidad de la base de datos
 */
public interface RespuestaDTO extends CodigoDescripcion<Number, String> {}
