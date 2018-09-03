package com.revature.pojo;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name = "Category")
public class Category implements Serializable{

	public Category() {
		super();
	}
	public Category(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySQ")
	@SequenceGenerator(allocationSize = 25, name = "categorySQ", sequenceName = "SQ_CATEGORY_PK")
	@Column (name = "CATEGORY_ID")
	private int id;
	@Column (name = "CATEGORY_NAME")
	private String name;
	@OneToMany(mappedBy = "Cat", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Flashcard> flashcards;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public List<Flashcard> getFlashcards(){
		return this.flashcards;
	}
	
}
