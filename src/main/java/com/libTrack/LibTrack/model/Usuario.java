package com.libTrack.LibTrack.model;

import com.libTrack.LibTrack.model.enums.TyperUser;

public abstract class Usuario {
    private long matricula;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private TyperUser typerUser;
}
