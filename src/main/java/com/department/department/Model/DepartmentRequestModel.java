package com.department.department.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentRequestModel {
    @JsonProperty("department-name")
    @NotBlank(message = "Department name can not be blank")
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;

    @JsonProperty("department-city")
    @NotBlank(message = "City can not be empty")
    @Size(min=2, message = "City should have at least 2 characters")
    private String city;

    @JsonProperty("department-state")
    @NotBlank(message = "State can not be empty")
    @Size(min=2, message = "State should have at least 2 characters")
    private String state;

    @JsonProperty("department-country")
    @NotBlank(message = "Country can not be empty")
    @Size(min=2, message = "Country should have at least 2 characters")
    private String country;

    @JsonProperty("zip-code")
    @NotBlank(message = "Zipcode can not be empty")
    @Size(min=5, message = "Zipcode should have at least 2 characters")
    private String zipcode;

}