package com.libTrack.LibTrack.service;

import com.libTrack.LibTrack.dto.request.AtivacaoRequest;
import com.libTrack.LibTrack.exception.RegraNegocioException;
import com.libTrack.LibTrack.model.TokenAtivacao;
import com.libTrack.LibTrack.model.Usuario;
import com.libTrack.LibTrack.model.enums.StatusUser;
import com.libTrack.LibTrack.repository.TokenAtivacaoRepository;
import com.libTrack.LibTrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AtivarContaService {
    private final TokenAtivacaoRepository tokenAtivacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AtivarContaService(TokenAtivacaoRepository tokenAtivacaoRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.tokenAtivacaoRepository = tokenAtivacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void ativarConta(AtivacaoRequest ativacaoRequest) {
        TokenAtivacao token = this.tokenAtivacaoRepository.findByToken(ativacaoRequest.getToken());
        if (!token.isValido()) {
            throw new RegraNegocioException("Token inválido ou expirado");
        }
        if(this.usuarioRepository.existsByLogin(ativacaoRequest.getLogin())) {
            throw new RegraNegocioException("Login já existente");
        }
        Usuario user = token.getUser();
        user.setLogin(ativacaoRequest.getLogin());
        user.setSenha(this.passwordEncoder.encode(ativacaoRequest.getSenha()));
        user.setStatusUser(StatusUser.ATIVADO);
        token.marcarComoUsado();
        this.usuarioRepository.save(user);
        this.tokenAtivacaoRepository.save(token);
    }
}
