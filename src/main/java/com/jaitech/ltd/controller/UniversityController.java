package com.jaitech.ltd.controller;

import com.jaitech.ltd.model.University;
import com.jaitech.ltd.service.UniversityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@Tag(name = "University API", description = "University API")
@RequestMapping("/university")
public class UniversityController {

   @Autowired
    private UniversityService universityService;

    // Create a new University
    @Operation(summary = "Create a University", description = "Create a University record", tags = {"university"}, responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ok",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = University.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(path = "/create")
    public ResponseEntity<Object> createUniversity(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    // Get a University by code
    @GetMapping(path ="/get/{code}")
    public ResponseEntity<Object> getUniversityByCode(@PathVariable final String code) {
        return universityService.getUniversityByCode(code);
    }

    // Search a University by name, country
    @GetMapping(path ="/search")
    public ResponseEntity<Object> searchUniversityByName(@RequestParam(required = false) final String name) {
        return universityService.searchUniversityByName(name);
    }

    // find all Universities
    @GetMapping(path ="/get-all")
    public ResponseEntity<Object> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    // Delete a University by code
    @DeleteMapping(path ="/delete/{code}")
    public ResponseEntity<Object> deleteUniversityByCode(@PathVariable final String code) {
        return universityService.deleteUniversityByCode(code);
    }
}
