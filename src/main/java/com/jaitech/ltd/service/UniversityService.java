package com.jaitech.ltd.service;

import com.jaitech.ltd.model.University;
import com.jaitech.ltd.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    /**
     * Create a new University
     *
     * @param university University
     * @return University
     */
    public ResponseEntity<Object> createUniversity(final University university) {

        if (university.getCode() == null || university.getCode().isEmpty()) {
            log.error("University Code is required");
            return ResponseEntity.badRequest().body("University Code is required");
        }

        val details = getUniversityDetails(university.getCode());
        if (details != null) {
            log.error("University already exists");
            return ResponseEntity.badRequest().body("University already exists");
        }

        if (university.getCreatedDateTime() == null) {
            university.setCreatedDateTime(String.valueOf(System.currentTimeMillis()));
        }

        log.info("Create University request: {}", university);

        return ResponseEntity.ok(universityRepository.save(university));
    }

    // Get a University by Id
    public ResponseEntity<Object> getUniversityByCode(final String code) {
        if (code == null || code.isEmpty()) {
            log.error("University Code is required");
            return ResponseEntity.badRequest().body("University Code is required");
        }

        val details = universityRepository.findById(code);
        if (details.isPresent()) {
            return ResponseEntity.ok(details.get());
        } else {
            log.error("University Code does not exist in the system {}", code);
            return ResponseEntity.badRequest().body("University Code does not exist in the system " + code);
        }
    }

    // Get all Universities
    public ResponseEntity<Object> getAllUniversities() {
        val details = universityRepository.findAll();

        if (details.isEmpty()) {
            log.error("No Universities found in the system");
            return ResponseEntity.badRequest().body("No Universities found in the system");
        } else {
            return ResponseEntity.ok(details);
        }
    }

    // Delete a University by code
    public ResponseEntity<Object> deleteUniversityByCode(final String code) {

        val details = universityRepository.findById(code);
        if (details.isPresent()) {
            try {
                universityRepository.deleteById(code);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                log.error("Error deleting University {}", e.getMessage());
                return ResponseEntity.badRequest().body("Error deleting University " + e.getMessage());
            }
        } else {
            log.error("University Code does not exist in the system {}", code);
            return ResponseEntity.badRequest().body("University Code does not exist in the system, can't delete it " + code);
        }
    }
    // Search a University by name
    public ResponseEntity<Object> searchUniversityByName(final String universityName) {
        if (universityName == null || universityName.isEmpty()) {
            log.error("University Name is required");
            return ResponseEntity.badRequest().body("University Name is required");
        }
        try {
            val details = universityRepository.findByNameContainingIgnoreCase(universityName);
            if (details != null) {
                return ResponseEntity.ok(details);
            } else {
                log.error("University Name does not exist in the system {}", universityName);
                return ResponseEntity.badRequest().body("University Name does not exist in the system " + universityName);
            }
        } catch (Exception e) {
            log.error("Error searching University {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error searching University " + e.getMessage());
        }
    }

    private University getUniversityDetails(final String code) {
        if (code == null || code.isEmpty()) {
            log.error("University Code is required");
            return null;
        }
        return universityRepository.findById(code).orElse(null);
    }


}
