package com.generalTemplate.adapter.database.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("EntityExample")
public class EntityExample implements Serializable {

    @Id
    private String id;

    private String date;
    private String comments;
}
