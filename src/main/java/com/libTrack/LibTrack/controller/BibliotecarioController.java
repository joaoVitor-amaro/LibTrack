package com.libTrack.LibTrack.controller;

import com.libTrack.LibTrack.dto.request.AlunoRequest;
import com.libTrack.LibTrack.dto.response.AlunoResponse;
import com.libTrack.LibTrack.dto.response.ApiResponse;
import com.libTrack.LibTrack.service.BibliotecarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bibliotecario")
@CrossOrigin("http://localhost:4200")
public class BibliotecarioController {
    private final BibliotecarioService bibliotecarioService;

    @Autowired
    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @PostMapping("/alunos")
    public ResponseEntity<ApiResponse<AlunoResponse>> cadastroAluno(@RequestBody @Valid AlunoRequest alunoRequest) {
        AlunoResponse aluno = this.bibliotecarioService.cadastrarAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Aluno cadastrado",
                LocalDateTime.now(),
                aluno
        ));
    }
}
