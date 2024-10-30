package com.generalTemplate.adapter.database.repository.implementation;

import com.generalTemplate.adapter.database.entity.EntityExample;
import com.generalTemplate.adapter.database.repository.RepositoryExample;
import com.generalTemplate.adapter.port.RepositoryExampleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RepositoryExampleImp implements RepositoryExampleInterface {

    @Autowired
    RepositoryExample repositoryExample;

    @Override
    public EntityExample CreateExampleEntry(EntityExample entityExample) {
        entityExample.setId(UUID.randomUUID().toString());
        return repositoryExample.save(entityExample);
    }

    @Override
    public EntityExample UpdateExampleEntry(EntityExample entityExample) {
        return repositoryExample.save(entityExample);
    }

    @Override
    public EntityExample GetExampleEntry(String id) {
        Optional<EntityExample> result = repositoryExample.findById(id);
        return result.orElse(null);
    }

    @Override
    public void DeleteExampleEntry(String id) {
        repositoryExample.deleteById(id);
    }

    @Override
    public List<EntityExample> GetAllExampleEntry() {
        List<EntityExample> result = new ArrayList<>();
        repositoryExample.findAll().forEach(result::add);
        return result;
    }
}
