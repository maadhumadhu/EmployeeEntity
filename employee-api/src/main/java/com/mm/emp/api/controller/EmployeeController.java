package com.mm.emp.api.controller;

import com.mm.emp.api.exception.ErrorEvent;
import com.mm.emp.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/empDetails")
@Tag(name = "EMPLOYEE",description = "Employee details")
public interface EmployeeController {

    @PostMapping(consumes = "application/json",produces = "application/json",value = "/save")
    Employee saveEmployee(@RequestHeader Map<String,String > headers, @RequestBody Employee emp, HttpServletResponse response);

    //http://localhost:8088/v1/empDetails/get?_page=2
    @GetMapping(produces = "application/json",value = "/get")
    @Operation(summary = "GET all employees",description = "This operation allow consumer to get all employee details in " +
            "page wise")
    //200 response is there by default, written just for understanding
    @ApiResponse(responseCode = "200",description = "OK",
    content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
    @ApiResponse(responseCode = "500",description = "Internal Server Error",
    content = {@Content(mediaType = "application/json",
    schema = @Schema(implementation = ErrorEvent.class))})
    List<Employee> getEmployees(@RequestHeader Map<String,String > headers, HttpServletResponse response, @Parameter(hidden = true) @RequestParam(name="_page",defaultValue = "1") String page,
                               @RequestParam(name="_perPage",defaultValue = "1") String size);

    //http://localhost:8088/v1/empDetails/get/101
    @GetMapping(produces = "application/json",value = "/get/{id}")
    Employee getEmployeeById(@RequestHeader Map<String,String > headers,@PathVariable long id,HttpServletResponse response);

    //http://localhost:8088/v1/empDetails/getbydetails?fName=Karthik&lname=Gopal&email=kg@gmail.com
    @GetMapping(produces = "application/json",value = "/getbydetails")
    Employee getEmployeeByFnameAndLnameAndEmail(@RequestHeader Map<String,String > headers,@RequestParam(value="fName") String fName,
                                                @RequestParam String lName,@RequestParam String email,HttpServletResponse response);
    @GetMapping("/test/from/github")
    public testMethodDev();
}
