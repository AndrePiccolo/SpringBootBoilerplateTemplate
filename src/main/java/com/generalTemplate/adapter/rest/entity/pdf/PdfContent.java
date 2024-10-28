package com.generalTemplate.adapter.rest.entity.pdf;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PdfContent {

    @JsonProperty("MainContent")
    private String mainContent;
}
