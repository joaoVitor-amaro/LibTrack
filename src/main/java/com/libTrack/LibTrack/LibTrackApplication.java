package com.libTrack.LibTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync	
public class LibTrackApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibTrackApplication.class, args);
	}

}
