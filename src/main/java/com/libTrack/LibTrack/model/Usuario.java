package com.libTrack.LibTrack.model;

import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.model.enums.TyperUser;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "matricula", nullable = false, unique = true)
    private long matricula;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "senha")
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "typeuser", nullable = false)
    private TyperUser typerUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusUser statusUser;

    public Usuario() {}

    public Usuario(long matricula, String nome, String email, String telefone, TyperUser typerUser, StatusUser statusUser) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.typerUser = typerUser;
        this.statusUser = statusUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TyperUser getTyperUser() {
        return typerUser;
    }

    public void setTyperUser(TyperUser typerUser) {
        this.typerUser = typerUser;
    }

    public StatusUser getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(StatusUser statusUser) {
        this.statusUser = statusUser;
    }
}
