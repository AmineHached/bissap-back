package com.bc.bissapp.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bc.bissapp.services.FileStorageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {

    @Autowired
    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String filename = fileStorageService.saveFile(file);
        return ResponseEntity.status(HttpStatus.OK).body("Fichier téléchargé avec succès: " + filename);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        try {
            byte[] Data = fileStorageService.loadFile(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(Data);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete/{filename}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        try {
            boolean deleted = fileStorageService.deleteFile(filename);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Fichier supprimé avec succès: " + filename);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier non trouvé: " + filename);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du fichier: " + e.getMessage());
        }
    }




}