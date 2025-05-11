package cl.duocuc.ecomarket.modelo.entity.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "usuario")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre_rol", nullable = false, length = 50)
    private String nombreRol;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @ColumnDefault("true")
    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "creado_el", updatable = false, nullable = false)
    private Instant creadoEl = Instant.now();

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolesPermiso> rolesPermisos = new LinkedHashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Instant getCreadoEl() {
        return creadoEl;
    }

    public void setCreadoEl(Instant creadoEl) {
        this.creadoEl = creadoEl;
    }

    public Set<RolesPermiso> getRolesPermisos() {
        return rolesPermisos;
    }

    public void setRolesPermisos(Set<RolesPermiso> rolesPermisos) {
        this.rolesPermisos = rolesPermisos;
    }
}