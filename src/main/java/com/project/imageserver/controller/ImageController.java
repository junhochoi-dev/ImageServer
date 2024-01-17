package com.project.imageserver.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import com.project.imageserver.data.request.ImageRequestDto;
import com.project.imageserver.data.response.ImageResponseDto;
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

    @GetMapping("/download/member")
    public ResponseEntity<?> downloadMember(
            @RequestBody ImageRequestDto imageRequestDto
    ) {
        List<String> list = imageService.downloadMember(imageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/upload/member")
    public ResponseEntity<?> uploadMember(
            @RequestBody ImageRequestDto imageRequestDto
    ) throws Exception {
        List<String> list = imageService.uploadMember(imageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/download/aws")
    public ResponseEntity<?> downloadAWS() throws IOException {
        System.out.println("[CONTROLLER][UPLOAD][AWS]");

        URL url = imageService.downloadAWS();
        System.out.println(url.getHost() + url.getFile());
        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][DOWNLOAD][AWS]");
    }
}
