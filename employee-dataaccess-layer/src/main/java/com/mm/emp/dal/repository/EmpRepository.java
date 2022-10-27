package com.mm.emp.dal.repository;


import com.mm.emp.dal.dao.EmpQueryParameters;
import com.mm.emp.model.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface EmpRepository extends CrudRepository<Employee,Long> , JpaSpecificationExecutor<Employee> {

    @Query(
            value = "SELECT * FROM employee WHERE id=:id",
            nativeQuery = true
    )
    Employee getEmployee(@Param("id") long id);


    @Query(
            value = "SELECT * FROM employee WHERE (email=:#{#empQueryParameters.email}) AND (f_name=:#{#empQueryParameters.fName}) AND (l_name=:#{#empQueryParameters.lName}) ",
            nativeQuery = true
    )
    Employee getEmployeeByDetails(@Param("empQueryParameters") EmpQueryParameters empQueryParameters);
}
