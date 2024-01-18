package com.project.imageserver.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record MultiImageRequestDto(
    @JsonProperty("sid")
    List<Long> idList
) { }
