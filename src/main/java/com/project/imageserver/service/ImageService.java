package com.project.imageserver.service;

import com.project.imageserver.domain.Image;

import org.springframework.stereotype.Service;

import com.project.imageserver.repository.StorageRepository;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

@Service
public class ImageService {
	private final StorageRepository storageRepository;

    public ImageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
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

		String PATH = "C:/Users/cjh97/Desktop/TESTDB/";
		String NAME = generateName() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

		//multipartFile.transferTo(new File(PATH + "TEST" + multipartFile.getOriginalFilename()));
		multipartFile.transferTo(new File(PATH + NAME));

		storageRepository.save(
			Image
				.builder()
				.path(PATH)
				.name(NAME)
				.extenstion(StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()))
				.build());

		return PATH + NAME;
	}
}
