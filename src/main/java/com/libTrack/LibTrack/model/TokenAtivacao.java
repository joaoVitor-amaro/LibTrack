package com.libTrack.LibTrack.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
public class TokenAtivacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    @Column(name = "data_expiracao", nullable = false)
    private Instant expiracao;
    @Column(name = "usado", nullable = false)
    private Boolean usado;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    public TokenAtivacao() {}

    public TokenAtivacao(String token, Usuario user) {
        this.token = token;
        this.user = user;
        this.usado = false;
        this.expiracao = Instant.now().plus(50, ChronoUnit.MINUTES);
    }

    public boolean isValido() {
        return !usado && Instant.now().isBefore(expiracao);
    }

    public void marcarComoUsado() {
        this.usado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(Instant expiracao) {
        this.expiracao = expiracao;
    }

    public Boolean getUsado() {
        return usado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
