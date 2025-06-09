package cl.duocuc.ecomarket.funcional;

import cl.duocuc.ecomarket.modelo.entity.usuario.Permiso;
import cl.duocuc.ecomarket.tipodatos.TipoPermiso;
import cl.duocuc.ecomarket.util.CodigoDescripcion;
import cl.duocuc.ecomarket.util.exception.ErrorDatos;

public class PermisoFuncional implements CodigoDescripcion<String, String> {

    private final String codigo;
    private final String descripcion;
    private final int cuantificable;

    public PermisoFuncional(Permiso entidad) throws ErrorDatos {
        this.codigo = entidad.getClavePermiso();
        this.descripcion = entidad.getDescripcion();
        try{
            this.cuantificable = TipoPermiso.valueOf(entidad.getClavePermiso().toUpperCase()).getValor();
        } catch (IllegalArgumentException e){
            throw new ErrorDatos("El permiso no es cuantificable", e);
        }
    }



    public int getCuantificable(){
        return cuantificable;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }
}
