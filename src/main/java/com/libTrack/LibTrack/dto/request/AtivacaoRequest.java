package com.libTrack.LibTrack.dto.request;

import jakarta.validation.constraints.NotBlank;

public class AtivacaoRequest {
    @NotBlank(message = "Token é obrigatório")
    private String token;
    @NotBlank(message = "Login é obrigatório")
    private String login;
    @NotBlank(message = "Senha é obrigatório")
    private String senha;

    public String getToken() {
        return token;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
