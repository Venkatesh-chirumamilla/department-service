package com.department.department.Shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DepartmentDto {

    private String id;
    private String name;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}