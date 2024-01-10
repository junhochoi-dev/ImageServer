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

	@GetMapping("/test")
	public ResponseEntity<?> test(){
		System.out.println("[CONTROLLER][TEST]");
		return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][TEST]");
	}
	@PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("image") MultipartFile file) throws IOException {
		System.out.println("[CONTROLLER][UPLOAD]");

        String uploadImage = imageService.upload(file);

		return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][UPLOAD]");
    }

//	// 다운로드
//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
//        //byte[] downloadImage = storageService.downloadImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(downloadImage);
//    }
}
