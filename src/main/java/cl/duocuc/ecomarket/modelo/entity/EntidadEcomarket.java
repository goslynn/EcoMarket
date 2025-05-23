package cl.duocuc.ecomarket.modelo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@MappedSuperclass
public class EntidadEcomarket implements Activable, Auditable {

    @Column(name = "fecha_creacion", insertable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant fechaCreacion;

    @Column(name = "activo", insertable = false)
    @ColumnDefault("true")
    private Boolean activo;

    @Override
    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public Boolean getActivo() {
        return activo;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
