package com.libTrack.LibTrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Aluno extends Usuario{
    @Column(name = "turma")
    private String turma;

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}
