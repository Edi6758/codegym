package com.empresa.suporte.model;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 200)
    private String nome;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String cpf;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private  String telefone;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String email;

    @NonNull
    @Column(unique = true)
    @Size(max = 100)
    private String login;

    @NonNull
    @Size(max = 100)
    private String senha;

    //-----------------------------
    @ManyToMany
    @JoinTable(
            name="usuario_permissao",
            joinColumns=@JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private List<Permissao> permissoes;

    //-----------------------------
    @ManyToMany
    @JoinTable(
            name="usuario_horario",
            joinColumns=@JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "horario_id")
    )
    private List<Horario> horarios;
    //-----------------------------
    @ManyToOne
    private Academia academia;
    //-----------------------------

    //GET SET


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
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    @NonNull
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NonNull String telefone) {
        this.telefone = telefone;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getSenha() {
        return senha;
    }

    public void setSenha(@NonNull String senha) {
        this.senha = senha;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Horario> getHorarioFuncionamentos() {
        return horarios;
    }

    public void setHorarioFuncionamentos(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", permissoes=" + permissoes +
                ", horarios=" + horarios +
                ", academia=" + academia +
                '}';
    }
}
