package com.project.imageserver.controller;

import java.io.File;
import java.io.IOException;
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

@RequiredArgsConstructor
@RestController
public class ImageController {
	private final ImageService imageService;
	@PostMapping("/upload/local")
    public ResponseEntity<?> uploadLocal(@RequestParam("image") MultipartFile file) throws IOException {
		System.out.println("[CONTROLLER][UPLOAD][LOCAL]");

        imageService.upload(file);

		return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD][LOCAL]");
    }

	@PostMapping("/upload/aws")
    public ResponseEntity<?> uploadAws(@RequestParam("image") MultipartFile file) throws IOException {
		System.out.println("[CONTROLLER][UPLOAD][AWS]");

        imageService.uploadFileToS3(file, "static/team-image");

		return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD][AWS]");
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
