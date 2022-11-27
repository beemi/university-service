package com.jaitech.ltd.service;

import com.jaitech.ltd.model.University;
import com.jaitech.ltd.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    /**
     * Create a new University
     * @param university University
     * @return University
     */
    public University createUniversity(final University university) {
        if (university.getName() == null || university.getName().isEmpty()) {
            log.error("University name is empty");
            throw new IllegalArgumentException("University name is not null");
        }

        if (university.getCreatedDateTime() == null) {
            university.setCreatedDateTime(String.valueOf(System.currentTimeMillis()));
        }

        log.info("Create University request: {}", university);

        return universityRepository.save(university);
    }

    // Get a University by Id
    public University getUniversityById(final String id) {
        return universityRepository.findById(id).get();
    }

    // Get all Universities
    public Iterable<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    // Delete a University by Id
    public void deleteUniversityById(final String id) {
        universityRepository.deleteById(id);
    }
}
