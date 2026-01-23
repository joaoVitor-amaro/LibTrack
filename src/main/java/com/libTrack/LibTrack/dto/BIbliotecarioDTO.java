package com.libTrack.LibTrack.dto;

import com.libTrack.LibTrack.model.Bibliotecario;
import com.libTrack.LibTrack.model.enums.TyperUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BIbliotecarioDTO {
    private Long matricula;
    private String nome;
    private String email;
    private String telefone;
    private TyperUser typerUser;

    public BIbliotecarioDTO(Bibliotecario bibliotecario) {
        this.matricula = bibliotecario.getMatricula();
        this.nome = bibliotecario.getNome();
        this.email = bibliotecario.getEmail();
        this.telefone = bibliotecario.getTelefone();
        this.typerUser = bibliotecario.getTyperUser();
    }

    public Long getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public TyperUser getTyperUser() {
        return typerUser;
    }
}
