package cl.duocuc.ecomarket.funcional;

import cl.duocuc.ecomarket.tipodatos.TipoPermiso;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequierePermiso {
    TipoPermiso value();
}
