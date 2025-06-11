package cl.duocuc.ecomarket.tipodatos;

import cl.duocuc.ecomarket.util.CodigoDescripcion;

public enum RecursoEcomarket {
    USUARIO(10, "Usuario"),
    CARRITO(20, "Carrito de Compras"),
    BODEGA(30, "Bodega"),
    PRODUCTO(31, "Producto"),
    PEDIDO(32, "Pedido");

    private final CodigoDescripcion<Integer, String> codigoDescripcion;

    RecursoEcomarket(String descripcion){
        this(0, descripcion);
    }

    RecursoEcomarket(Integer codigo, String descripcion){
        this.codigoDescripcion = CodigoDescripcion.of(codigo, descripcion);
    }


    public CodigoDescripcion<Integer, String> getCodigoDescripcion() {
        return codigoDescripcion;
    }

    public Integer getCodigo() {
        return codigoDescripcion.getCodigo();
    }

    public String getDescripcion() {
        return codigoDescripcion.getDescripcion();
    }

    public static RecursoEcomarket valueOf(Integer codigo) {
        for (RecursoEcomarket recurso : values()) {
            if (recurso.getCodigo().equals(codigo)) {
                return recurso;
            }
        }
        throw new IllegalArgumentException("No se encontró un recurso con el código: " + codigo);
    }
}
