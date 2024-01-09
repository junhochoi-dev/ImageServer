package com.project.imageserver.controller;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.imageserver.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	private ImageService imageService;

	@PostMapping("/upload")
	public String upload(@RequestBody List<MultipartFile> files){
		System.out.println(files.size());

		String path = "C:/Users/cjh97/Desktop/";
		for(int i = 0; i < files.size(); i++){
			String filename = "TEST" + i;
			String extension = '.' + Objects.requireNonNull(files.get(i).getOriginalFilename())
				.replaceAll("^.*\\.(.*)$", "$1");
			File file = new File(path + filename + extension);

			System.out.println(path + filename + extension);
		}
		return "GOOD";
	}
}
