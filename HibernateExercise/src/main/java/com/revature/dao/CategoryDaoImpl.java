package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public CategoryDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		
		Session s = sf.openSession();
		List<Category> cl = null;
		
		try {
			cl = s.createQuery("from Category").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sf.close();
		
		return cl;
	}

	@Override
	public Category getCategoryById(int id) {
		Session s = sf.openSession();
		Category c = null;
		
		try {
			c = (Category) s.get(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sf.close();
		return c;
	}

	@Override
	public int addCategory(Category c) {
		Session s = sf.openSession();
		int id = 0;
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		sf.close();
		
		return id;
	}

	@Override
	public void updateCategory(Category c) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		sf.close();


	}

	@Override
	public void deleteCategory(Category c) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(c);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		sf.close();

	}

}
