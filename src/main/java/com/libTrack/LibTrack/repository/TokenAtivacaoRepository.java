package com.libTrack.LibTrack.repository;

import com.libTrack.LibTrack.model.TokenAtivacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenAtivacaoRepository extends JpaRepository<TokenAtivacao, Long> {
    TokenAtivacao findByToken(String token);
}
