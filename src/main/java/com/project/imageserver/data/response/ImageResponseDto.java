package com.project.imageserver.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ImageResponseDto {
    @JsonProperty("id")
    private List<String> list;
}
