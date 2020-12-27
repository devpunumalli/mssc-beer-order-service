package com.dev.msscbeerorderseervice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@RequiredArgsConstructor

public class BaseItem {

    @JsonProperty("id)")
    private UUID id;
    @Positive
    private Integer version;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",shape = JsonFormat.Shape.STRING)
    @JsonProperty("createdDate")
    private OffsetDateTime lastModifiedDate;
}
