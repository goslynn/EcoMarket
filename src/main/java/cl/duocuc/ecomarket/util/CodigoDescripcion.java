package cl.duocuc.ecomarket.util;

public interface CodigoDescripcion<Codigo, Descripcion> {
    Codigo getCodigo();
    Descripcion getDescripcion();

    default String getCodigoDescripcion() {
        return String.format("%s - %s", getCodigo(), getDescripcion());
    }
}
