package com.libTrack.LibTrack.controller;

import com.libTrack.LibTrack.dto.request.AtivacaoRequest;
import com.libTrack.LibTrack.dto.response.ApiResponse;
import com.libTrack.LibTrack.service.AtivarContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ativar")
@CrossOrigin("http://localhost:4200")
public class AtivarContaController {
    private final AtivarContaService ativarContaService;

    @Autowired
    public AtivarContaController(AtivarContaService ativarContaService) {
        this.ativarContaService = ativarContaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> ativarConta(@RequestBody @Valid AtivacaoRequest ativacaoRequest) {
        this.ativarContaService.ativarConta(ativacaoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                200,
                "Conta ativada",
                LocalDateTime.now(),
                null
        ));
    }
}
