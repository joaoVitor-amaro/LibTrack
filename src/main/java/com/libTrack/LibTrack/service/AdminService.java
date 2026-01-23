package com.libTrack.LibTrack.service;

import com.libTrack.LibTrack.dto.BIbliotecarioDTO;
import com.libTrack.LibTrack.dto.request.BibliotecarioCreateRequest;
import com.libTrack.LibTrack.dto.response.ApiResponse;
import com.libTrack.LibTrack.exception.RegraNegocioException;
import com.libTrack.LibTrack.model.Bibliotecario;
import com.libTrack.LibTrack.model.enums.TyperUser;
import com.libTrack.LibTrack.repository.BibliotecarioRepository;
import com.libTrack.LibTrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final BibliotecarioRepository bibliotecarioRepository;

    @Autowired
    public AdminService(UsuarioRepository usuarioRepository, BibliotecarioRepository bibliotecarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.bibliotecarioRepository = bibliotecarioRepository;
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
        this.bibliotecarioRepository.save(bibliotecario);
        return new BIbliotecarioDTO(bibliotecario);
    }
}
