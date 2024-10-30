package com.generalTemplate.adapter.database.repository;

import com.generalTemplate.adapter.database.entity.EntityExample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryExample extends CrudRepository<EntityExample, String> {}
