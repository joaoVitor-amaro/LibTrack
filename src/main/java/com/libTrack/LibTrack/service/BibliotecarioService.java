package com.libTrack.LibTrack.service;

import com.libTrack.LibTrack.dto.request.AlunoRequest;
import com.libTrack.LibTrack.dto.response.AlunoResponse;
import com.libTrack.LibTrack.exception.RegraNegocioException;
import com.libTrack.LibTrack.model.Aluno;
import com.libTrack.LibTrack.model.TokenAtivacao;
import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.model.enums.TyperUser;
import com.libTrack.LibTrack.repository.AlunoRepository;
import com.libTrack.LibTrack.repository.TokenAtivacaoRepository;
import com.libTrack.LibTrack.repository.UsuarioRepository;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecarioService {
    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final EmailService emailService;
    private final TokenAtivacaoService tokenAtivacaoService;
    private final TokenAtivacaoRepository tokenAtivacaoRepository;

    @Autowired
    public BibliotecarioService(UsuarioRepository usuarioRepository, AlunoRepository alunoRepository, EmailService emailService, TokenAtivacaoService tokenAtivacaoService, TokenAtivacaoRepository tokenAtivacaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.emailService = emailService;
        this.tokenAtivacaoService = tokenAtivacaoService;
        this.tokenAtivacaoRepository = tokenAtivacaoRepository;
    }

    public AlunoResponse cadastrarAluno(AlunoRequest alunoRequest) {
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
        String token = this.tokenAtivacaoService.gerarToken();
        this.emailService.sendEmail(
                aluno.getEmail(),
                "Ativação de conta",
                "Clique no link: http://localhost:4200/ativar?token=" + token
        );
        TokenAtivacao tokenAtivacao = new TokenAtivacao(token, aluno);
        this.alunoRepository.save(aluno);
        this.tokenAtivacaoRepository.save(tokenAtivacao);
        return new AlunoResponse(aluno);
    }
}
