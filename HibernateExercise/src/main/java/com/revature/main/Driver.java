package com.revature.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.beans.CategoryStatus;
import com.revature.beans.Flashcard;
import com.revature.dao.CategoryDao;
import com.revature.dao.CategoryDaoImpl;
import com.revature.dao.FlashcardDao;
import com.revature.dao.FlashcardDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		
		//CategoryDao c = new CategoryDaoImpl();
		
		//Category ca = c.getCategoryById(4);
		
		
		FlashcardDao fc = new FlashcardDaoImpl();
		System.out.println(fc.getFlashcards());
	
	
	}
	
	
}
