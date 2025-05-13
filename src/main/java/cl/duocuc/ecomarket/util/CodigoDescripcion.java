package cl.duocuc.ecomarket.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"codigo", "descripcion"})
public interface CodigoDescripcion<Codigo, Descripcion> {

    Codigo getCodigo();

    Descripcion getDescripcion();

    @JsonIgnore
    default String getCodigoDescripcion() {
        return String.format("%s - %s", getCodigo(), getDescripcion());
    }
}
