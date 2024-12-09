package com.day.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProjectDto {

	@NotBlank(message = "The cod is required.")
	private String cod;
	@NotBlank(message = "The name is required.")
	private String name;
	@Positive(message = "The budget is required.")
	private String budget;
	@PositiveOrZero(message = "The cost is required.")
	private String cost;

	public ProjectDto() {
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

}
