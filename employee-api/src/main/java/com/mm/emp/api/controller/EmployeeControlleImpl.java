package com.mm.emp.api.controller;

import com.mm.emp.api.service.EmpService;
import com.mm.emp.model.Employee;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
public class EmployeeControlleImpl implements EmployeeController{

    public EmpService empService;

    @Autowired
    public EmployeeControlleImpl(EmpService empService){
        this.empService=empService;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(Map<String, String> headers, Employee emp, HttpServletResponse response) {
        return empService.saveEmployee(headers,emp,response);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getEmployees(Map<String, String> headers, HttpServletResponse response, String page, String size) {
        int pageValue=Integer.parseInt(page);
        int sizeValue=Integer.parseInt(size);
        Pageable pageable= PageRequest.of(pageValue-1,sizeValue);
        return empService.getEmployees(headers,response,pageable);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(Map<String, String> headers, long id, HttpServletResponse response) {
        return empService.getEmployeeById(headers,id,response);
    }

    @Override
    public Employee getEmployeeByFnameAndLnameAndEmail(Map<String, String> headers, String fname, String lname, String email, HttpServletResponse response) {
        return empService.getEmployeeByDetails(headers,fname,lname,email,response);
    }
}
