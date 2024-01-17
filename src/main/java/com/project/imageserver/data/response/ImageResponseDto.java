package com.project.imageserver.data.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ImageResponseDto {
    private List<MultipartFile> ImageList;
}
