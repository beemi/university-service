package com.jaitech.ltd.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "universitycollection")
public class University {

    @Id
    @NotNull
    @Parameter(description = "University Code", required = true)
    private String code;
    @Parameter(description = "University name", required = true)
    private String name;
    @Parameter(description = "University email")
    private String email;
    @Parameter(description = "University phone")
    private String phone;
    @Parameter(description = "University domains")
    private List<String> domains;
    @Parameter(description = "University web pages")
    private List<String> webpages;
    @Parameter(description = "University address")
    private String address;
    @Parameter(description = "University city")
    private String city;
    @Parameter(description = "University state")
    private String state;
    @Parameter(description = "University zip")
    private String zip;
    @Parameter(description = "University country")
    private String country;
    @Parameter(description = "University created date and time")
    private String createdDateTime;
    @Parameter(description = "University updated date and time")
    private String updatedDateTime;
}
