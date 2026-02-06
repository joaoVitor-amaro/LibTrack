package com.libTrack.LibTrack.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenAtivacaoService {

    public String gerarToken() {
        return UUID.randomUUID().toString();
    }
}
