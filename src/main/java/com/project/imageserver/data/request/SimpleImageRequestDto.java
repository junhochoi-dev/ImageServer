package com.project.imageserver.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SimpleImageRequestDto(
        @JsonProperty("sid")
        Long id
) { }
