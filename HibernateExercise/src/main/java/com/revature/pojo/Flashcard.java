package com.revature.pojo;


import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name = "Flashcard")
public class Flashcard implements Serializable{

	public Flashcard() {
		super();
	}
	public Flashcard(String question, String answer, Category C) {
		this.question = question;
		this.answer = answer;
		this.Cat = C;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashcardSQ")
	@SequenceGenerator(allocationSize = 25, name = "flashcardSQ", sequenceName = "SQ_FLASHCARD_PK")
	@Column (name = "FLASHCARD_ID")
	private int id;
	@Column (name = "QUESTION")
	private String question;
	@Column (name = "ANSWER")
	private String answer;
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn (name = "CATEGORY_ID")
	private Category Cat;
	
	public String getQuestion() {
		return this.question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCat() {
		return this.Cat;
	}
	public void setCat(Category C) {
		this.Cat = C;
	}
	public String toString() {
		return this.question;
	}
	
}
