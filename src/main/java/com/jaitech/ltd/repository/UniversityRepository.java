package com.jaitech.ltd.repository;

import com.jaitech.ltd.model.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository  extends MongoRepository<University, String> {

    Object findByNameContainingIgnoreCase(final String universityName);
}
