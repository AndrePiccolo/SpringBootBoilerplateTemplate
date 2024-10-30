package com.generalTemplate.adapter.rest.entity.testdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DBEntryTest {

    @JsonProperty("id")
    private String id;
    @JsonProperty("date")
    private String date;
    @JsonProperty("comments")
    private String comments;
}
