package com.nmuzychuk.hrdb;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Cacheable("employees")
    @Transactional
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map((e) -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
