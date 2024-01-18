package com.project.imageserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        log.info("TEST SUCCESS");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
