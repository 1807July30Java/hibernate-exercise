package com.revature.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.openSession();
		
		CategoryDao c = new CategoryDaoImpl();
		//System.out.println(c.getCategoryById(2));
		//c.updateCategory(new Category(2, "Fantasy", CategoryStatus.ACTIVE));
		FlashcardDao f = new FlashcardDaoImpl();
//		String q = "What are those?!?";
//		String a = "Best dawg";
//		Category c1 = c.getCategoryById(2);
//		f.addFlashcard(new Flashcard(q, a, c1));
//		System.out.println(f.getFlashcards());
		
	}

}
