package com.project.imageserver.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.imageserver.service.ImageService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload/local")
    public ResponseEntity<?> uploadLocal(@RequestParam("image") MultipartFile file) throws IOException {
        System.out.println("[CONTROLLER][UPLOAD][LOCAL]");

        imageService.uploadLocal(file);

        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD][LOCAL]");
    }

    @PostMapping("/upload/aws")
    public ResponseEntity<?> uploadAWS(@RequestParam("image") MultipartFile file) throws IOException {
        System.out.println("[CONTROLLER][UPLOAD][AWS]");

        imageService.uploadAWS(file, "static/team-image");

        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD][AWS]");
    }

    @PostMapping("/download/local")
    public ResponseEntity<?> uploadLocal() throws IOException {
        System.out.println("[CONTROLLER][UPLOAD][LOCAL]");
        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][DOWNLOAD][LOCAL]");
    }
    @GetMapping("/download/aws")
    public ResponseEntity<?> downloadAWS() throws IOException {
        System.out.println("[CONTROLLER][UPLOAD][AWS]");

        URL url = imageService.downloadAWS();
        System.out.println(url.getHost() + url.getFile());
        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][DOWNLOAD][AWS]");
    }
    // @GetMapping("/download")
    // public ResponseEntity<?> download() throws IOException {
    // 	String uploadImage = imageService.upload(file);
    //
    // 	return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD]");
    // }


//	// 다운로드
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
//        //byte[] downloadImage = storageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//    }
}
