package com.libTrack.LibTrack.controller;

import com.libTrack.LibTrack.dto.BIbliotecarioDTO;
import com.libTrack.LibTrack.dto.request.BibliotecarioCreateRequest;
import com.libTrack.LibTrack.dto.response.ApiResponse;
import com.libTrack.LibTrack.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:4200")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/bibliotecarios")
    public ResponseEntity<ApiResponse<BIbliotecarioDTO>> cadastroBibliotecario(@RequestBody @Valid BibliotecarioCreateRequest bibliotecarioCreateRequest) {
        BIbliotecarioDTO response = adminService.cadastroBibliotecario(bibliotecarioCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED.value(),
                        "Bibliotecário cadastrado",
                        LocalDateTime.now(),
                        response
                ));
    }
}
