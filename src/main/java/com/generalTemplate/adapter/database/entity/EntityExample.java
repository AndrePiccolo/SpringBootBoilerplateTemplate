package com.generalTemplate.adapter.database.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("Example")
public class EntityExample implements Serializable {

    private String id;
    private Date date;
    private String comments;
}
