package com.jaitech.ltd.controller;

import com.jaitech.ltd.model.University;
import com.jaitech.ltd.service.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/university")
public class UniversityController {

   @Autowired
    private UniversityService universityService;

    // Create a new University
    @RequestMapping(value="/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json", headers = "Accept=application/json")
    public University createUniversity(@RequestBody University university) {
        return universityService.createUniversity(university);
    }

    // Get a University by Id
    @RequestMapping(value="/get", method = RequestMethod.GET, consumes = "application/json", produces = "application/json", headers = "Accept=application/json")
    public University getUniversityById(final String id) {
        return universityService.getUniversityById(id);
    }

}
