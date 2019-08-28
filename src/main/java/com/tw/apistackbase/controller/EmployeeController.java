package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.bean.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private static List<Employee> employees=new ArrayList<Employee>() {{
		add(new Employee(1, "xiaozhang", 13, "female"));
		add(new Employee(2, "xiaolu", 14, "male"));
	}};
//	@GetMapping
//	public ResponseEntity<List<Employee>> getEmployees(){
//		return ResponseEntity.ok(employees);
//	}
	@GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
		for (Employee employee : employees) {
			if(employee.getId()==id) {
				return ResponseEntity.ok(employee);		
		}
		}
        return null;
    }
	@GetMapping("/employee")
	public ResponseEntity<Employee> getEmployeeByGender(@RequestParam("name") String gender) {
	    for (Employee employee : employees) {
	    	if (employee.getGender().equals(gender)) {
	            return ResponseEntity.ok(employee);
	        }
		}
	    return null;
	}
	@PostMapping
	public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee){
		employees.add(employee);
		return ResponseEntity.ok(employee);
	}
	@PutMapping(consumes = "application/json")
	public ResponseEntity<Employee> putEmployee(@RequestBody Employee employeeUpdate) {
	   for (Employee employee: employees) {
		   if (employee.getId() == employeeUpdate.getId()) {
			   employee.setName(employeeUpdate.getName());
			   employee.setAge(employeeUpdate.getAge());
			   employee.setGender(employeeUpdate.getGender());
	        }
	}
	    return ResponseEntity.status(HttpStatus.OK).build();
	}
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable int id) {
	    employees.remove(id);
	}
}
