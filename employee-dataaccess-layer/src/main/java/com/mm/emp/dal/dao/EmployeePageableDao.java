package com.mm.emp.dal.dao;


import com.mm.emp.dal.repository.EmpPageableRepository;
import com.mm.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class EmployeePageableDao extends EmployeeDao{

    @Autowired
    EmpPageableRepository empPageableRepository;

    public Page<Employee> getEmployees(Pageable pageable, Map<String,String> pageMap){
        return this.empPageableRepository.getEmployees(pageable);
    }

}
