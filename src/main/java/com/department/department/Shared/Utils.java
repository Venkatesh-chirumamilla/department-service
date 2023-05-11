package com.department.department.Shared;

import com.department.department.Data.DepartmentEntity;
import com.department.department.Exception.NotFoundException;
import com.department.department.Model.DepartmentRequestModel;
import com.department.department.Model.DepartmentResponseModel;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component

public class Utils {
    public List<DepartmentResponseModel> getDepartmentResponseModelList(List<DepartmentDto> departments) throws NotFoundException {

        if (departments == null || departments.isEmpty()) {
            throw new NotFoundException("0 records found");
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<DepartmentResponseModel> departmentsList = new ArrayList<>();
        departments.forEach((departmentDto -> {
            DepartmentResponseModel departmentResponseModel = modelMapper.map(departmentDto, DepartmentResponseModel.class);
            departmentsList.add(departmentResponseModel);
        }));


        return departmentsList;
    }
    public DepartmentResponseModel getDepartmentResponseModel(DepartmentDto departmentDto) throws NotFoundException {
        if (departmentDto == null) {
            throw new NotFoundException("Department not found");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper.map(departmentDto, DepartmentResponseModel.class);
    }

    public DepartmentDto getDepartmentDto(DepartmentRequestModel departmentDetails) throws NotFoundException {
        if(departmentDetails == null){
            throw new NotFoundException("Request object is null");
        }

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(departmentDetails, DepartmentDto.class);
    }
    public List<DepartmentDto> getDepartnmentDtoList(Iterable<DepartmentEntity> departments){
        List<DepartmentDto> allDepartments = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        long count = StreamSupport.stream(departments.spliterator(), false).count();
        if(count > 0){
            departments.forEach(departmentEntity -> {
                DepartmentDto departmentDto = modelMapper.map(departmentEntity, DepartmentDto.class);
                allDepartments.add(departmentDto);
            });
        }else{
            throw new ValidationException("List of entities is null or empty");
        }

        return allDepartments;
    }
}
