package com.yqz.springboot.quickstart.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Person {
	@NotNull
	@Min(value = 1L, message = "id must be greater than 1")
	private Integer id;
	@NotNull
	@Length(min = 2, max = 10, message = "name length is between 2 and 10")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
