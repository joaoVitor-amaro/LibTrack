package com.libTrack.LibTrack.repository;

import com.libTrack.LibTrack.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByLogin(String login);
}
