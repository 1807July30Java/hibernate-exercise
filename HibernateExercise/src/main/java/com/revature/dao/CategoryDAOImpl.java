package com.revature.dao;
import com.revature.pojo.Category;
import com.revature.pojo.Flashcard;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class CategoryDAOImpl implements CategoryDAO {
	public List<Flashcard> getFlashcardsByCategory(SessionFactory sf,int categoryid){
		Session s = sf.openSession();
		Category c = (Category) s.get(Category.class, categoryid);
		s.close();
		return c.getFlashcards();
	}
	public int newCategory(SessionFactory sf,String name) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Category c = new Category(name);
		int newid = (int) s.save(c);
		tx.commit();
		s.close();
		return newid;
	}
		
	public Category getCategoryById(SessionFactory sf,int categoryid) {
		Session s = sf.openSession();
		Category c = (Category) s.get(Category.class, categoryid);
		s.close();
		return c;
	}
}
