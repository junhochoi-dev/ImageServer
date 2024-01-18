package com.project.imageserver.data.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class MultiImageResponseDto {
    private List<Long> idList;
    private List<String> referenceList;
}
