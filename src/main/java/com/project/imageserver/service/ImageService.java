package com.project.imageserver.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.project.imageserver.data.request.SimpleImageRequestDto;
import com.project.imageserver.data.response.ImageResponseDto;
import com.project.imageserver.domain.Image;
import com.project.imageserver.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.imageserver.repository.StorageRepository;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
	private final StorageRepository storageRepository;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private String PATH_ROOT = "storage/";
	private String PATH_MEMBER = "member/";
	private String PATH_FEED = "feed/";

	public Long uploadMember(SimpleImageRequestDto simpleImageRequestDto) throws Exception {
		MultipartFile image = simpleImageRequestDto.getFile();

		String reference = UUID.randomUUID().toString();
		String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());

		String filepath = PATH_ROOT + PATH_MEMBER + reference + "." + extension;

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(image.getContentType());
		metadata.setContentLength(image.getSize());

		amazonS3Client.putObject(bucket, filepath, image.getInputStream(), metadata);

		Long id = storageRepository.save(
					Image.builder()
							.reference(reference)
							.extenstion(extension)
							.build()
			).getId();
		return id;
    }

	public String downloadMember(SimpleImageRequestDto simpleImageRequestDto){
		Image image = storageRepository.findById(simpleImageRequestDto.getId()).get();
		return image.getReference() + "." + image.getExtenstion();
	}

	public void delete(SimpleImageRequestDto simpleImageRequestDto){
		Long id = simpleImageRequestDto.getId();
		Image image = storageRepository.findById(id).get();
		String filename = PATH_ROOT + PATH_MEMBER + image.getReference() + "." + image.getExtenstion();
		storageRepository.deleteById(id);
		amazonS3Client.deleteObject(bucket, filename);
	}
}
