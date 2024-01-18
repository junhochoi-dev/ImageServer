package com.project.imageserver.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SimpleImageResponseDto(
    @JsonProperty("sid")
    Long id,
    @JsonProperty("path")
    String path
) { }
