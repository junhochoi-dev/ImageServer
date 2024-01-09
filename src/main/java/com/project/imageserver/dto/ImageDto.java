package com.project.imageserver.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageDto {
	private MultipartFile file;
	private String caption;
}
