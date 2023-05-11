package com.department.department.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentResponseModel {

    private String id;

    @JsonProperty("department-name")
    private String name;

    @JsonProperty("department-city")
    private String city;

    @JsonProperty("department-state")
    private String state;

    @JsonProperty("department-country")
    private String country;

    @JsonProperty("zip-code")
    private String zipcode;


}