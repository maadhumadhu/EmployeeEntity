package com.mm.emp.api.service;


import com.mm.emp.model.Employee;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface EmpService {

    Employee saveEmployee(Map<String, String> headers, Employee emp, HttpServletResponse response);
    List<Employee> getEmployees(Map<String, String> headers, HttpServletResponse response, Pageable pageable);
    Employee getEmployeeById(Map<String, String> headers, long id, HttpServletResponse response);

    Employee getEmployeeByDetails(Map<String, String> headers, String fname, String lname, String email, HttpServletResponse response);
}
