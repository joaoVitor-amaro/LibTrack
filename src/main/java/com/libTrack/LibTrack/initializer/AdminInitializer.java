package com.libTrack.LibTrack.initializer;

import com.libTrack.LibTrack.model.Admin;
import com.libTrack.LibTrack.model.enums.TyperUser;
import com.libTrack.LibTrack.repository.AdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer {
    private AdminRepository adminRepository;
    @Autowired

    public AdminInitializer(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    public void criarAdminPadrao() {
        if(!this.adminRepository.existsByLogin("admin")) {
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setSenha("admin123");
            admin.setNome("Administrator");
            admin.setMatricula(202045638);
            admin.setEmail("admin@library.com");
            admin.setTelefone("(83) 98788-6513");
            admin.setTyperUser(TyperUser.ADMIN);
            adminRepository.save(admin);
        }
    }
}
