package com.generalTemplate.adapter.rest.pdf.entity;

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
