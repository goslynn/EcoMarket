package cl.duocuc.ecomarket.modelo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.ColumnDefault;

@MappedSuperclass
public class EntidadActivable implements Activable {

    @Column(name = "activo", insertable = false)
    @ColumnDefault("true")
    private Boolean activo;

    @Override
    public Boolean getActivo() {
        return activo;
    }

    @Override
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
