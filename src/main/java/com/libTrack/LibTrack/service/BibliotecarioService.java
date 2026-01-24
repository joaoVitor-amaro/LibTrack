package com.libTrack.LibTrack.service;

import com.libTrack.LibTrack.dto.request.AlunoRequest;
import com.libTrack.LibTrack.dto.response.AlunoResponse;
import com.libTrack.LibTrack.exception.RegraNegocioException;
import com.libTrack.LibTrack.model.Aluno;
import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.model.enums.TyperUser;
import com.libTrack.LibTrack.repository.AlunoRepository;
import com.libTrack.LibTrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecarioService {
    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;

    @Autowired
    public BibliotecarioService(UsuarioRepository usuarioRepository, AlunoRepository alunoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse cadastrarBibliotecario(AlunoRequest alunoRequest) {
        if(this.usuarioRepository.existsByMatricula(alunoRequest.getMatricula())) {
            throw new RegraNegocioException("Matricula já cadastrada");
        }
        if(this.usuarioRepository.existsByEmail(alunoRequest.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        Aluno aluno = new Aluno();
        aluno.setMatricula(alunoRequest.getMatricula());
        aluno.setNome(alunoRequest.getNome());
        aluno.setEmail(alunoRequest.getEmail());
        aluno.setTelefone(alunoRequest.getTelefone());
        aluno.setTurma(alunoRequest.getTurma());
        aluno.setTyperUser(TyperUser.ALUNO);
        aluno.setStatusUser(StatusUser.PENDENTE);
        this.alunoRepository.save(aluno);
        return new AlunoResponse(aluno);
    }
}
