package com.libTrack.LibTrack.repository;

import com.libTrack.LibTrack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByMatricula(Long matricula);
    boolean existsByLogin(String login);
}
