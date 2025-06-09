package cl.duocuc.ecomarket.tipodatos;

import cl.duocuc.ecomarket.util.CodigoDescripcion;

public enum ModuloEcommerce {
    CARRITO("Carrito de Compras"),
    PRODUCTO("Producto"),
    CATEGORIA("Categoria"),
    PEDIDO("Pedido"),
    USUARIO(1, "Usuario");

    private final CodigoDescripcion<Integer, String> codigoDescripcion;

    ModuloEcommerce(String descripcion){
        this(0, descripcion);
    }

    ModuloEcommerce(Integer codigo, String descripcion){
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
}
