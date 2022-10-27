package com.mm.emp.api.service;


import com.mm.emp.dal.dao.EmpQueryParameters;
//import com.mm.emp.dal.dao.EmployeeDao;
import com.mm.emp.dal.dao.EmployeePageableDao;
import com.mm.emp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService{

//    @Autowired
//    EmployeeDao employeeDao;

    @Autowired
    EmployeePageableDao empPageDao;

    Page<Employee> empRecordPage;

    @Autowired
    public EmpServiceImpl(EmployeePageableDao empPageDao){
        this.empPageDao=empPageDao;
    }

    @Override
    public Employee saveEmployee(Map<String, String> headers, Employee emp, HttpServletResponse response) {
        return null;
    }

    @Override
    public List<Employee> getEmployees(Map<String, String> headers, HttpServletResponse response, Pageable pageable) {
        Map<String,String> pageMap=new HashMap<>();
        empRecordPage=empPageDao.getEmployees(pageable,pageMap);
        pageMap.put("TotalRecords",String.valueOf(empRecordPage.getTotalElements()));
        pageMap.put("LastRecordReceived",String.valueOf(!empRecordPage.hasNext()));
        System.out.println("##############");
        System.out.println(empRecordPage.hasNext());
        System.out.println("##############");

        response.addIntHeader("TotalRecords",Integer.parseInt(pageMap.get("TotalRecords")));
        response.addHeader("LastRecordReceived",pageMap.get("LastRecordReceived"));
        response.addIntHeader("_page",pageable.getPageNumber()+1);
        response.addIntHeader("_perPage",pageable.getPageSize());

        headers.put("TotalRecords",pageMap.get("TotalRecords"));
        headers.put("LastRecordReceived",String.valueOf(empRecordPage.hasNext()));
        headers.put("_page",String.valueOf(pageable.getPageNumber()+1));
        headers.put("_perPage",String.valueOf(pageable.getPageSize()));
        return empRecordPage.getContent();
    }

    @Override
    public Employee getEmployeeById(Map<String, String> headers, long id, HttpServletResponse response) {
        return empPageDao.getEmployee(id);
    }

    @Override
    public Employee getEmployeeByDetails(Map<String, String> headers, String fname, String lname, String email, HttpServletResponse response) {
        EmpQueryParameters empQueryParameters = new EmpQueryParameters();
        empQueryParameters.setEmail(email);
        empQueryParameters.setfName(fname);
        empQueryParameters.setlName(lname);
        return empPageDao.getEmployeeByDetails(empQueryParameters);
    }
}
