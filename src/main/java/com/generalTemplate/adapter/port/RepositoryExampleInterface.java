package com.generalTemplate.adapter.port;

import com.generalTemplate.adapter.database.entity.EntityExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RepositoryExampleInterface {

    EntityExample CreateExampleEntry(EntityExample entityExample);
    EntityExample UpdateExampleEntry(EntityExample entityExample);
    EntityExample GetExampleEntry(String id);
    void DeleteExampleEntry(String id);
    List<EntityExample> GetAllExampleEntry();
}
