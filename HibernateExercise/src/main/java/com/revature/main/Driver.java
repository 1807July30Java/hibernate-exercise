package com.revature.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dao.CategoryDAO;
import com.revature.dao.CategoryDAOImpl;
import com.revature.dao.FlashcardDAO;
import com.revature.dao.FlashcardDAOImpl;
import com.revature.pojo.Category;
import com.revature.pojo.Flashcard;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		FlashcardDAO fd = new FlashcardDAOImpl();
		CategoryDAO cd = new CategoryDAOImpl();
		try {
			int newcat = cd.newCategory(sf, "geography");
			int newid = fd.newFlashcard(sf, "what's the name of Michelle Obamas Husband", "Barrack",cd.getCategoryById(sf, newcat));
			Flashcard f = fd.getFlashcardById(sf, newid);
			System.out.println(newid);
			
			Category c = cd.getCategoryById(sf, newcat);
			fd.newFlashcard(sf, "what is the largest desert in the world", "sahara", c);
			System.out.println(cd.getFlashcardsByCategory(sf, c.getId()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	static void init(SessionFactory sf) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Flashcard f = new Flashcard("who is the drummer from led zepplin","John Bonham",new Category("music"));
		s.save(f);

		tx.commit();

		s.close();
	}
}
