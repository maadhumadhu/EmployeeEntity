package com.mm.emp.dal.dao;


import com.mm.emp.dal.repository.EmpRepository;
import com.mm.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    @Autowired
    EmpRepository empRepository;


    public Employee getEmployee(long id){
        Employee emp=empRepository.getEmployee(id);
        return emp;
        //return empRepository.getEmployee(id);
    }

    public Employee getEmployeeByDetails(EmpQueryParameters empQueryParameters){
        return empRepository.getEmployeeByDetails(empQueryParameters);
    }
}
