package com.bc.bissapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bc.bissapp.config.ProjectMetadata;

@SpringBootApplication
public class BissappApplication {

	public static void main(String[] args) {
		// Print ownership watermark on startup
		System.out.println("\n" + "=".repeat(60));
		System.out.println(ProjectMetadata.getProjectIdentity());
		System.out.println("=".repeat(60) + "\n");
		
		SpringApplication.run(BissappApplication.class, args);
	}

}
