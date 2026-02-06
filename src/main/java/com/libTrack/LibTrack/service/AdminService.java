package com.libTrack.LibTrack.service;

import com.libTrack.LibTrack.dto.BIbliotecarioDTO;
import com.libTrack.LibTrack.dto.request.BibliotecarioCreateRequest;
import com.libTrack.LibTrack.exception.RegraNegocioException;
import com.libTrack.LibTrack.model.Bibliotecario;
import com.libTrack.LibTrack.model.TokenAtivacao;
import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.model.enums.TyperUser;
import com.libTrack.LibTrack.repository.BibliotecarioRepository;
import com.libTrack.LibTrack.repository.TokenAtivacaoRepository;
import com.libTrack.LibTrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final BibliotecarioRepository bibliotecarioRepository;
    private final EmailService emailService;
    private final TokenAtivacaoRepository tokenAtivacaoRepository;
    private final TokenAtivacaoService tokenAtivacaoService;

    @Autowired
    public AdminService(UsuarioRepository usuarioRepository, BibliotecarioRepository bibliotecarioRepository, EmailService emailService, TokenAtivacaoRepository tokenAtivacaoRepository, TokenAtivacaoService tokenAtivacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.bibliotecarioRepository = bibliotecarioRepository;
        this.emailService = emailService;
        this.tokenAtivacaoRepository = tokenAtivacaoRepository;
        this.tokenAtivacaoService = tokenAtivacaoService;
    }

    public BIbliotecarioDTO cadastroBibliotecario(BibliotecarioCreateRequest bibliotecarioCreateRequest) {
        if(this.usuarioRepository.existsByEmail(bibliotecarioCreateRequest.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado");
        }

        if (this.usuarioRepository.existsByMatricula(bibliotecarioCreateRequest.getMatricula())) {
            throw new RegraNegocioException("Matrícula já cadastrada");
        }
        Bibliotecario bibliotecario = new Bibliotecario();
        bibliotecario.setMatricula(bibliotecarioCreateRequest.getMatricula());
        bibliotecario.setNome(bibliotecarioCreateRequest.getNome());
        bibliotecario.setEmail(bibliotecarioCreateRequest.getEmail());
        bibliotecario.setTelefone(bibliotecarioCreateRequest.getTelefone());
        bibliotecario.setTyperUser(TyperUser.BIBLIOTECARIO);
        bibliotecario.setStatusUser(StatusUser.PENDENTE);
        String token = this.tokenAtivacaoService.gerarToken();
        this.emailService.sendEmail(
                bibliotecario.getEmail(),
                "Ativação de conta",
                "Clique no link: http://localhost:4200/ativar?token=" + token
        );
        this.bibliotecarioRepository.save(bibliotecario);
        TokenAtivacao tokenAtivacao = new TokenAtivacao(token, bibliotecario);
        this.tokenAtivacaoRepository.save(tokenAtivacao);
        return new BIbliotecarioDTO(bibliotecario);
    }
}
