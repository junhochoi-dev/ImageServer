package com.project.imageserver.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.project.imageserver.utils.S3Uploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.imageserver.repository.StorageRepository;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
public class ImageService {
	private final StorageRepository storageRepository;
	private final S3Uploader s3Uploader;

	private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

    public ImageService(StorageRepository storageRepository, AmazonS3Client amazonS3Client, S3Uploader s3Uploader, AmazonS3Client amazonS3Client1) {
        this.storageRepository = storageRepository;
        this.s3Uploader = s3Uploader;
        this.amazonS3Client = amazonS3Client1;
    }

	public String generateName(){
		String characters = "0123456789abcdefghijqlmnopqrstuwxyzABCDEFGHIJQLMNOPQRSTUWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();

		for (int i=0; i < 100; i++) {
			int index = random.nextInt(characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}

    public String upload(MultipartFile multipartFile) throws IOException{
		System.out.println("[SERVICE][UPLOAD]");

		String PATH = "C:/Users/SSAFY/Desktop/TESTDB/";
		String NAME = generateName() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

		//multipartFile.transferTo(new File(PATH + "TEST" + multipartFile.getOriginalFilename()));
		multipartFile.transferTo(new File(PATH + NAME));

//		storageRepository.save(
//			Image
//				.builder()
//				.path(PATH)
//				.name(NAME)
//				.extenstion(StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()))
//				.build());

		return PATH + NAME;
	}

	public ResponseEntity<String> uploadFileToS3(MultipartFile file, String filePath) {
        // MultipartFile -> File 로 변환
		try {
				String fileName = file.getOriginalFilename();
				String fileUrl= "https://" + bucket + "/test" +fileName;
				ObjectMetadata metadata= new ObjectMetadata();
				metadata.setContentType(file.getContentType());
				metadata.setContentLength(file.getSize());
				amazonS3Client.putObject(bucket,fileName,file.getInputStream(),metadata);
				return ResponseEntity.ok(fileUrl);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
}
