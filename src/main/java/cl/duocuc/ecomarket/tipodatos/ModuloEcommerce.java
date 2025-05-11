package cl.duocuc.ecomarket.tipodatos;

import cl.duocuc.ecomarket.util.CodigoDescripcion;

public enum ModuloEcommerce {
    CARRITO("Carrito de Compras"),
    PRODUCTO("Producto"),
    CATEGORIA("Categoria"),
    PEDIDO("Pedido"),
    USUARIO(1, "Usuario");

    private final CodigoDescripcion<Integer, String> codigoDescripcion;

    ModuloEcommerce(Integer codigo, String descripcion){
        this.codigoDescripcion = new CodigoDescripcion<>() {
            @Override
            public Integer getCodigo() {
                return codigo;
            }

            @Override
            public String getDescripcion() {
                return descripcion;
            }
        };
    }

    ModuloEcommerce(String descripcion){
        this.codigoDescripcion = new CodigoDescripcion<>() {
            @Override
            public Integer getCodigo() {
                return 0;
            }

            @Override
            public String getDescripcion() {
                return descripcion;
            }
        };
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
