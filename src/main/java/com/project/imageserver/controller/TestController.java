package com.project.imageserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        System.out.println("[CONTROLLER][TEST]");
        return ResponseEntity.status(HttpStatus.OK).body("[CONTROLLER][TEST]");
    }
}
