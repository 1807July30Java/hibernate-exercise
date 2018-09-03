package com.revature.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.revature.pojo.Category;
import com.revature.pojo.Flashcard;

public interface CategoryDAO {
	public List<Flashcard> getFlashcardsByCategory(SessionFactory sf,int categoryid);
	public Category getCategoryById(SessionFactory sf,int categoryid);
	public int newCategory(SessionFactory sf,String name);

}
