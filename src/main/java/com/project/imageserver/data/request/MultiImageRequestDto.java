package com.project.imageserver.data.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class MultiImageRequestDto {
    private List<Long> idList;
    private List<MultipartFile> imageList;
}
