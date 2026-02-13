package com.libTrack.LibTrack.model;

import com.libTrack.LibTrack.dto.request.AlunoRequest;
import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.model.enums.TyperUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Aluno extends Usuario{
    @Column(name = "turma")
    private String turma;

    public Aluno() {}

    public Aluno(AlunoRequest aluno) {
        super(aluno.getMatricula(), aluno.getNome(), aluno.getEmail(), aluno.getTelefone(), TyperUser.ALUNO, StatusUser.PENDENTE);
        this.turma = aluno.getTurma();
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}
