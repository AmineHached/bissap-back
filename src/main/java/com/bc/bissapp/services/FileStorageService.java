package com.bc.bissapp.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("src/main/resources/uploads");

    // creer le dossier de stockage des fichiers s'il n'existe pas
    public FileStorageService() {

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("error lors de la création de dossier de stockage.", e);
        }

    }

    // Sauvegarder un fichier uploadé
    public String saveFile(MultipartFile file) {
        try {
            // Préfixer le nom de fichier avec le timestamp pour éviter les doublons
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Impossible de sauvegarder le fichier. " + e.getMessage());
        }
    }

    // Lire un fichier pour le téléchargement
    public byte[] loadFile(String filename) throws IOException {
        Path file = rootLocation.resolve(filename);
        return Files.readAllBytes(file);
    }

    public boolean deleteFile(String filename) throws IOException {
        return Files.deleteIfExists(rootLocation.resolve(filename));
    }

}
