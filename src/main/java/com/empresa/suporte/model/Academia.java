package com.empresa.suporte.model;

import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "academia")
public class Academia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 200)
    private String nome;

    @NonNull
    @Size(max = 200)
    private String cnpj;

    //-----------------------------
    @OneToMany
    private List<Usuario> usuarios;
    //-----------------------------

  //GET e SET


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(@NonNull String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }



    @Override
    public String toString() {
        return "Academia{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}
