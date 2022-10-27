package com.mm.emp.dal.repository;

import com.mm.emp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpPageableRepository extends EmpRepository {

    @Query(
            value = "SELECT * FROM employee ORDER BY id",
            countQuery = "SELECT * FROM employee",
            nativeQuery = true
    )
    Page<Employee> getEmployees(Pageable pageable);
}
