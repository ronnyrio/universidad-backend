package com.springsimplespasos.universidad.universidadbackend.modelo.entidades;

import com.springsimplespasos.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarra;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "aulas")
public class Aula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name ="numeroAula", nullable = false)
    private Integer nroAula;
    @Column (name ="medidas_m2")
    private String medidas;
    @Column (name ="cantidad_pupitres")
    private Integer cantidadPupitres;
    @Column (name ="topo_pizarra")
    @Enumerated (EnumType.STRING)
    private Pizarra pizarra;
    @Column (name ="fecha_alta")
    private LocalDateTime fechaAlta;
    @Column (name ="fecha_Modificaion")
    private LocalDateTime fechaUltimaModificaion;

    public Aula(Integer id, Integer nroAula, String medidas, Integer cantidadPupitres, Pizarra pizarra, LocalDateTime fechaAlta, LocalDateTime fechaUltimaModificaion) {
        this.id = id;
        this.nroAula = nroAula;
        this.medidas = medidas;
        this.cantidadPupitres = cantidadPupitres;
        this.pizarra = pizarra;
        this.fechaAlta = fechaAlta;
        this.fechaUltimaModificaion = fechaUltimaModificaion;
    }

    public Aula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", nroAula=" + nroAula +
                ", medidas='" + medidas + '\'' +
                ", cantidadPupitres=" + cantidadPupitres +
                ", pizarra=" + pizarra +
                ", fechaAlta=" + fechaAlta +
                ", fechaUltimaModificaion=" + fechaUltimaModificaion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return id.equals(aula.id) && cantidadPupitres.equals(aula.cantidadPupitres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cantidadPupitres);
    }

    public Integer getNroAula() {
        return nroAula;
    }

    public void setNroAula(Integer nroAula) {
        this.nroAula = nroAula;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public Integer getCantidadPupitres() {
        return cantidadPupitres;
    }

    public void setCantidadPupitres(Integer cantidadPupitres) {
        this.cantidadPupitres = cantidadPupitres;
    }

    public Pizarra getPizarra() {
        return pizarra;
    }

    public void setPizarra(Pizarra pizarra) {
        this.pizarra = pizarra;
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

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }
    @PreUpdate
    private void antesDeUpdate(){
        this.fechaUltimaModificaion = LocalDateTime.now();
    }

}
