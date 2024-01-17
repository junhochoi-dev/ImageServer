package com.project.imageserver.data.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ImageRequestDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("images")
    private List<MultipartFile> list;
}
