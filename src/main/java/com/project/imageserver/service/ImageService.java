package com.project.imageserver.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.project.imageserver.data.request.ImageRequestDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
	private final StorageRepository storageRepository;
	private final S3Uploader s3Uploader;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private String PATH_MEMBER = "/member";
	private String PATH_FEED = "/feed";

	public List<String> uploadMember(ImageRequestDto imageRequestDto) throws Exception {
		List<String> list = new ArrayList<>();

		for(MultipartFile image : imageRequestDto.getList()){
			String id = UUID.randomUUID().toString();
			String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());

			String filename = PATH_MEMBER + "/" + id + "." + extension;

			list.add(filename);

			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(image.getContentType());
			metadata.setContentLength(image.getSize());

			amazonS3Client.putObject(bucket, filename, image.getInputStream(), metadata);

			storageRepository.save(
					Image
							.builder()
							.path(null)
							.name(null)
							.extenstion(null)
							.type(null)
							.build()
			);
		}
		return list;
    }

	public List<String> downloadMember(ImageRequestDto imageRequestDto){
		// DB 조회후 ID 제공
		return null;
	}
	public URL downloadAWS(){
        System.out.println(amazonS3Client.getUrl(bucket, "ABC"));
		return amazonS3Client.getUrl(bucket, "ABC");
    }
}
