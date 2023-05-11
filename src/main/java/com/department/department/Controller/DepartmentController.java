package com.department.department.Controller;

import com.department.department.Exception.NotFoundException;
import com.department.department.Model.DepartmentRequestModel;
import com.department.department.Model.DepartmentResponseModel;
import com.department.department.Service.DepartmentService;
import com.department.department.Shared.DepartmentDto;
import com.department.department.Shared.Utils;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    Utils utils;
    @GetMapping
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments() throws NotFoundException{
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return new ResponseEntity<>(utils.getDepartmentResponseModelList(departments), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponseModel> createDepartments(@Valid @RequestBody DepartmentRequestModel departmentDetails) throws NotFoundException {

        String name = departmentDetails.getName();
        if (departmentService.getDepartmentByName(name) != null) {
            throw new ValidationException("Department with this name already exists");
        }

        DepartmentDto departmentDto = utils.getDepartmentDto(departmentDetails);
        DepartmentDto createdValue = departmentService.createDepartments(departmentDto);

        return new ResponseEntity<>(utils.getDepartmentResponseModel(createdValue), HttpStatus.CREATED);


    }


    @GetMapping("/id/{id}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentById(@PathVariable Integer id) throws NotFoundException {
        DepartmentDto departmentDto = departmentService.getDepartmentById(id);

        return new ResponseEntity<>(utils.getDepartmentResponseModel(departmentDto), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentByName(@PathVariable String name) throws NotFoundException {
        DepartmentDto departmentDto = departmentService.getDepartmentByName(name);
        return new ResponseEntity<>(utils.getDepartmentResponseModel(departmentDto), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DepartmentResponseModel>> searchEmployees(
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city) throws NotFoundException {
        List<DepartmentDto> departments = null;
        if(state != null && city != null){
            departments = departmentService.getDepartmentByStateAndCity(state,city);
        }else if(state != null){
            departments = departmentService.getDepartmentByState(state);
        }else if(city != null){
            departments = departmentService.getDepartmentByCity(city);
        }


        return new ResponseEntity<>(utils.getDepartmentResponseModelList(departments), HttpStatus.OK);
    }


    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<DepartmentResponseModel> updateDepartment(@Valid @RequestBody DepartmentRequestModel departmentDetails) throws NotFoundException {
        String name = departmentDetails.getName();
        if (departmentService.getDepartmentByName(name) == null) {
            throw new ValidationException("User not found, failed to update");
        }

        //Getting existing user
        DepartmentDto existingUser = departmentService.getDepartmentByName(name);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        DepartmentDto departmentDto = modelMapper.map(departmentDetails, DepartmentDto.class);
        departmentDto.setId(existingUser.getId());

        DepartmentDto createdValue = departmentService.createDepartments(departmentDto);


        return new ResponseEntity<>(utils.getDepartmentResponseModel(createdValue), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> delete(@PathVariable String name) {
        if (departmentService.getDepartmentByName(name) == null) {
            throw new ValidationException("Department with this name already exists");
        }
        boolean res = departmentService.deleteDepartmentByName(name);
        if (res) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new ValidationException("error occurred while deleting");
    }
}
