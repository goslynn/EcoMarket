package cl.duocuc.ecomarket.modelo.dto;

import java.util.function.Function;

public final class DTOMapper {

    //FIXME no me acuerdo para que puse esto... xd
    public static <E, D extends RespuestaDTO> D convertir(E entidad, Function<E, D> factory) {
        return factory.apply(entidad);
    }



}
