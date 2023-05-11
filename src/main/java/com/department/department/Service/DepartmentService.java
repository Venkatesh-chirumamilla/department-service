package com.department.department.Service;

import com.department.department.Shared.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartments(DepartmentDto departmentDetails);
    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(long id);

    DepartmentDto getDepartmentByName(String name);

    List<DepartmentDto> getDepartmentByCity(String city);
    List<DepartmentDto> getDepartmentByState(String state);
    List<DepartmentDto> getDepartmentByCountry(String country);

    List<DepartmentDto> getDepartmentByZipcode(String zipcode);
    boolean deleteDepartmentByName(String name);


    List<DepartmentDto> getDepartmentByStateAndCity(String state, String city);
}