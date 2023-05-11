package com.department.department.Service;

import com.department.department.Data.DepartmentEntity;
import com.department.department.Data.DepartmentRepository;
import com.department.department.Shared.DepartmentDto;
import com.department.department.Shared.Utils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    Utils utils;
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,Utils utils){
        this.departmentRepository = departmentRepository;
        this.utils = utils;
    }
    @Override

    public DepartmentDto createDepartments(DepartmentDto departmentDetails)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDetails, DepartmentEntity.class);
        departmentRepository.save(departmentEntity);
        return modelMapper.map(departmentEntity,DepartmentDto.class);

    }
    @Override
    public List<DepartmentDto> getAllDepartments() {
        Iterable<DepartmentEntity> departments = departmentRepository.findAll();



        return utils.getDepartnmentDtoList(departments);
    }

    @Override
    public DepartmentDto getDepartmentById(long id){
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(id);
        return new ModelMapper().map(departmentEntity, DepartmentDto.class);
    }
    @Override
    public DepartmentDto getDepartmentByName(String name) {
        DepartmentEntity departmentEntity = departmentRepository.findByName(name);
        if(departmentEntity == null){
            return null;
        }else{
            return new ModelMapper().map(departmentEntity, DepartmentDto.class);
        }
    }
    @Override
    public List<DepartmentDto> getDepartmentByCity(String city) {
        Iterable<DepartmentEntity> departments = departmentRepository.findByCity(city);

        return utils.getDepartnmentDtoList(departments);
    }

    @Override
    public List<DepartmentDto> getDepartmentByState(String state) {
        Iterable<DepartmentEntity> departments = departmentRepository.findByState(state);

        return utils.getDepartnmentDtoList(departments);
    }
    @Override
    public List<DepartmentDto> getDepartmentByCountry(String country) {
        Iterable<DepartmentEntity> departments = departmentRepository.findByCountry(country);

        return utils.getDepartnmentDtoList(departments);
    }
    @Override
    public List<DepartmentDto> getDepartmentByZipcode(String zipcode) {
        Iterable<DepartmentEntity> departments = departmentRepository.findByZipcode(zipcode);

        return utils.getDepartnmentDtoList(departments);
    }
    @Override
    public boolean deleteDepartmentByName(String name) {
        try{
            departmentRepository.deleteByName(name);
            return true;
        }catch(Exception ex){
            return false;
        }
    }


    @Override

    public List<DepartmentDto> getDepartmentByStateAndCity(String state, String city) {
        Iterable<DepartmentEntity> departments = departmentRepository.findByStateAndCity(state, city);

        return utils.getDepartnmentDtoList(departments);
    }
}