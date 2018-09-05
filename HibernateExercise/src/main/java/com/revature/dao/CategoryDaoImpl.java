package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {
	
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		Session s = sf.openSession();
		List<Category> cat = null;
		
		try {
			cat = s.createQuery("from Category").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return cat;
	}

	@Override
	public Category getCategoryById(int id) {
		Session s = sf.openSession();
		Category cat = null;
		
		try {
			cat = (Category) s.get(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return cat;
	}

	@Override
	public int addCategory(Category c) {
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		int catId = 0;
		
		try {
			catId = (int) s.save(c);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		s.close();
		
		return catId;
	}

	@Override
	public void updateCategory(Category c) {
		Session s = sf.openSession();
		Transaction tr= s.beginTransaction();
		
		try {
			s.merge(c);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		s.close();
	}

	@Override
	public void deleteCategory(Category c) {
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		try {
			s.delete(c);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		s.close();
	}

}
