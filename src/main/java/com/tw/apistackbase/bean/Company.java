package com.tw.apistackbase.bean;

import java.util.List;

public class Company {
	private int id;
	private String baseInformation;
	private List<Employee> employee;
	public Company() {}
	public Company(int id, String baseInformation, List<Employee> employee) {
		this.id = id;
		this.baseInformation = baseInformation;
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaseInformation() {
		return baseInformation;
	}
	public void setBaseInformation(String baseInformation) {
		this.baseInformation = baseInformation;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
}
