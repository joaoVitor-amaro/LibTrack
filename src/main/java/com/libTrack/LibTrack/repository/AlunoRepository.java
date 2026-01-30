package com.libTrack.LibTrack.repository;

import com.libTrack.LibTrack.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
