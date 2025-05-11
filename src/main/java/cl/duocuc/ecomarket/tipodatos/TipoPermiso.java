package cl.duocuc.ecomarket.tipodatos;

/**
 * Enum que posee todos los permisos conocidos y evaluables por el sistema
 * Son representados por un codigo de 3 digitos.
 */
public enum TipoPermiso {
    CREAR_USUARIO(NivelPermiso.CREACION, ModuloEcommerce.USUARIO),
    VER_USUARIOS(NivelPermiso.LECTURA, ModuloEcommerce.USUARIO),
    EDITAR_USUARIO(NivelPermiso.EDICION, ModuloEcommerce.USUARIO),
    CREAR_VENTA(NivelPermiso.CREACION, ModuloEcommerce.PEDIDO),
    VER_VENTAS(NivelPermiso.LECTURA, ModuloEcommerce.PEDIDO);

    final int valorCuantificable;

    /**
     * Extrae un valor cuantificable a partir de un nivel de permiso y un modulo
     * (modulo al que pertenece este permiso).
     * El formato del valor cuantificable siempre debe resultar en un numero de 3 digitos <br>
     * El primer digito siempre va a representar la centena extraida desde el {@link NivelPermiso} <br>
     * los segundos 2 digitos se rellenan con el modificador (valor numerico de {@link ModuloEcommerce}), rellena con 0 a la derecha si son necesarios.
     * Se espera que el modificador sea un valor entre 0 y 99
     * @param nivelPermiso primer digito del valor cuantificable
     * @param modulo modificador del valor cuantificable
     */
    TipoPermiso(NivelPermiso nivelPermiso, ModuloEcommerce modulo){
        int nivel = nivelPermiso.getNivel(); //100
        int centena = nivel / 100; //1
        int modificador = modulo.getCodigo(); //1

        if (modificador < 0 || modificador > 99) {
            throw new IllegalArgumentException("El código del módulo debe estar entre 0 y 99");
        }

        //String.format se encarga de rellenar con 0 a la derecha si hiciese falta
        String resultado = String.format("%d%02d", centena, modificador);
        this.valorCuantificable = Integer.parseInt(resultado);
    }

    public TipoPermiso valueOf(int numero) {
        for (TipoPermiso t : TipoPermiso.values()) {
            if (t.valorCuantificable == numero) {
                return t;
            }
        }
        throw new IllegalArgumentException("No existe un tipo de permiso con el valor: " + numero);
    }

    public int getValor() {
        return valorCuantificable;
    }

}
