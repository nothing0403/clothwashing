package com.example.demo.model.entity;

public enum Role {

	Admin("管理員"),
	Employee("廠內員工"),
	Driver("司機");
	
	public final String roleName;
	
	Role(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}
