package com.libTrack.LibTrack.dto.response;

import com.libTrack.LibTrack.model.Aluno;

public class AlunoResponse {
    private Long matricula;
    private String nome;
    private String email;
    private String telefone;
    private String turma;

    public AlunoResponse(Aluno aluno) {
        this.matricula = aluno.getMatricula();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.telefone = aluno.getTelefone();
        this.turma = aluno.getTurma();
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

    public String getTurma() {
        return turma;
    }
}
