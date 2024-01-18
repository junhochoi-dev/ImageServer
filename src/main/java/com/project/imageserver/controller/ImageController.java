package com.project.imageserver.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.project.imageserver.data.request.MultiImageRequestDto;
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
    public ResponseEntity<?> downloadMember(@RequestBody SimpleImageRequestDto simpleImageRequestDto) {
        log.info("Download Member API");
        SimpleImageResponseDto simpleImageResponseDto = imageService.downloadMember(simpleImageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(simpleImageResponseDto);
    }

    @PostMapping("/upload/member")
    public ResponseEntity<?> uploadMember(@RequestPart("image") MultipartFile file) throws Exception {
        log.info("Upload Member API");
        return ResponseEntity.status(HttpStatus.OK).body(imageService.uploadMember(file));
    }

    @PostMapping("/download/feed")
    public ResponseEntity<?> downloadFeed(@RequestBody MultiImageRequestDto multiImageRequestDto) {
        log.info("Download Feed API");
        return null;
    }

    @PostMapping("/upload/feed")
    public ResponseEntity<?> uploadFeed(@RequestPart("image") List<MultipartFile> files) throws Exception {
        log.info("Upload Feed API");
        return null;
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        log.info("Delete API");
        SimpleImageRequestDto simpleImageRequestDto = SimpleImageRequestDto.builder().id(id).build();
        imageService.delete(simpleImageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
