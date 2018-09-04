package com.revature.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category implements Serializable {

	public Category(int id, String name, CategoryStatus isActive) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
	
	public Category(String name, CategoryStatus isActive) {
		super();
		this.name = name;
		this.isActive = isActive;
	}

	public Category() {
		super();
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -4505332504426261530L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="categorySequence")
	@SequenceGenerator(allocationSize=1,name="categorySequence",sequenceName="SQ_CATEGORY_PK")
	@Column(name="CATEGORY_ID")
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="IS_ACTIVE")
	private CategoryStatus isActive;

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

	public CategoryStatus getIsActive() {
		return isActive;
	}

	public void setIsActive(CategoryStatus isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}

}