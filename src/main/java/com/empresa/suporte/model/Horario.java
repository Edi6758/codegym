package com.empresa.suporte.model;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private java.time.LocalTime hora;

    @NonNull
    @Basic
    private String semana;

    private int limite;

    @ManyToMany(mappedBy = "horarios")
    private List<Usuario> usuarios;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @NonNull
    public String getSemana() {
        return semana;
    }

    public void setSemana(@NonNull String semana) {
        this.semana = semana;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return
                "Hora: " + hora + "  " +
                "  " + semana;
    }
}
