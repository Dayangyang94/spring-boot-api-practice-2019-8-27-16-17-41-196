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

import com.tw.apistackbase.bean.Company;
import com.tw.apistackbase.bean.Employee;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
	private static List<Employee> employees = new ArrayList<Employee>() {{
		add(new Employee(1,"xiaozhang", 13, "female"));
		add(new Employee(2,"xiaolu", 14, "male"));
	}};
	private static List<Company> companies = new ArrayList<Company>() {{
		// 公司：id  基本信息  员工
		add(new Company(1,"百度",employees));	
	}};
	
	// 获取列表  查询
	@GetMapping
	public ResponseEntity<List<Company>> queryCompanies() {
		
		return ResponseEntity.ok(companies);
	}
	
//	//获取某一个特定的公司
//	@GetMapping(path = "/{id}")
//    public ResponseEntity<Company> queryCompany(@PathVariable Integer id) {
//		for(Company company:companies) {
//       	if(company.getId()==id) {
//       		 return ResponseEntity.ok(company);      
//       	}
//        }
//        return null;
//	}
	
	//获取某一个公司下的所有员工
		@GetMapping(path = "/{id}")
	    public ResponseEntity<List<Employee>> queryEmployees(@PathVariable Integer id) {
			for(Company company:companies) {
	       	if(company.getId()==id) {
	       		List<Employee> employees=company.getEmployee();  
	       		return ResponseEntity.ok(employees);   
	       	}
	        }
	        return null;
		}
		
      //分页查询	
		@GetMapping("/pages")
	    public ResponseEntity<List<Company>> getCompaiesByPage(@RequestParam int page,@RequestParam int pageSize){			
			if(companies.size()<=(page-1)*pageSize) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        else {
	            List<Company> pageCompanies = new ArrayList<>();
	            for(int i=(page-1)*pageSize;i<companies.size()&&i<page*pageSize;i++){
	              pageCompanies.add(companies.get(i));
	            }
	            return ResponseEntity.ok(pageCompanies);
	        }
		}
		
 	 //添加一个新的公司
		@PostMapping(consumes="application/json")
		public ResponseEntity<Company> addCompany(@RequestBody Company company){
			companies.add(company);
			return ResponseEntity.status(HttpStatus.CREATED).build();   
		}	
		
	//更新一个公司的基本信息	
	    @PutMapping(consumes="application/json")
		public ResponseEntity<Company> updateCompany(@RequestBody Company company){
	    	companies.add(company);
			return ResponseEntity.status(HttpStatus.OK).build();   
		}	
   //删除一个公司
	    @DeleteMapping("/{id}")
		public ResponseEntity<Employee> deleteEmployeeByID(@PathVariable Integer id){
			//service.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).build();   
		} 
	    
}