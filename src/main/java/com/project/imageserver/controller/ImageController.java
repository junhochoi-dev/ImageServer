package com.project.imageserver.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import com.project.imageserver.data.request.SimpleImageRequestDto;
import com.project.imageserver.data.response.SimpleImageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.imageserver.service.ImageService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/download/member")
    public ResponseEntity<?> downloadMember(
            @RequestParam("id") Long id
    ) {
        SimpleImageRequestDto simpleImageRequestDto = SimpleImageRequestDto.builder().id(id).build();
        SimpleImageResponseDto simpleImageResponseDto = imageService.downloadMember(simpleImageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("sid", simpleImageResponseDto.getReference()));
    }

    @PostMapping("/upload/member")
    public ResponseEntity<?> uploadMember(
            @RequestParam("image") MultipartFile file
    ) throws Exception {
        SimpleImageRequestDto simpleImageRequestDto = SimpleImageRequestDto.builder().file(file).build();
        SimpleImageResponseDto simpleImageResponseDto = imageService.uploadMember(simpleImageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("sid", simpleImageResponseDto.getId()));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestParam("id") Long id
    ) {
        SimpleImageRequestDto simpleImageRequestDto = SimpleImageRequestDto.builder().id(id).build();
        imageService.delete(simpleImageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
