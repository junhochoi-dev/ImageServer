package com.project.imageserver.service;

import com.project.imageserver.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.imageserver.repository.StorageRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
	private final StorageRepository storageRepository;

    public ImageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String upload(MultipartFile multipartFile) throws IOException{
		System.out.println("[SERVICE][UPLOAD]");
		String PATH = "C:/Users/SSAFY/Desktop";
		multipartFile.transferTo(new File(PATH + multipartFile.getOriginalFilename()));
		Image image = storageRepository.save(
				Image.builder()
						.name(multipartFile.getOriginalFilename())
						.type(multipartFile.getContentType())
						.build());
		if(image != null) {
			return "file upload sucessfully : " + multipartFile.getOriginalFilename();
		}

		return null;
	}
}
