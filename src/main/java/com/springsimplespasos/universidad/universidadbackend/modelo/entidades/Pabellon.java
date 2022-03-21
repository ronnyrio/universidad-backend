package com.springsimplespasos.universidad.universidadbackend.modelo.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pabellones")
public class Pabellon implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name ="medidas_m2")
    private Double mts2;
    @Column (name ="nombre_pabellon", unique = true,nullable = false)
    private String nombre;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "dpto", column = @Column(name = "departamento"))
    })
    private Dirección dirección;
    @Column (name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column (name ="fecha_Modificaion")
    private LocalDateTime fechaUltimaModificaion;
    @OneToMany(
            mappedBy = "pabellon",
            fetch = FetchType.LAZY
    )
    private Set<Aula> aulas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pabellon pabellon = (Pabellon) o;
        return id.equals(pabellon.id) && nombre.equals(pabellon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Pabellon{" +
                "id=" + id +
                ", mts2=" + mts2 +
                ", nombre='" + nombre + '\'' +
                ", dirección=" + dirección +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimaModificaion=" + fechaUltimaModificaion +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMts2() {
        return mts2;
    }

    public void setMts2(Double mts2) {
        this.mts2 = mts2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Dirección getDirección() {
        return dirección;
    }

    public void setDirección(Dirección dirección) {
        this.dirección = dirección;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaUltimaModificaion() {
        return fechaUltimaModificaion;
    }

    public void setFechaUltimaModificaion(LocalDateTime fechaUltimaModificaion) {
        this.fechaUltimaModificaion = fechaUltimaModificaion;
    }

    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }
    @PreUpdate
    private void antesDeUpdate(){
        this.fechaUltimaModificaion = LocalDateTime.now();
    }

    public Pabellon(Integer id, Double mts2, String nombre, Dirección dirección) {
        this.id = id;
        this.mts2 = mts2;
        this.nombre = nombre;
        this.dirección = dirección;
    }

    public Pabellon() {
    }

}
