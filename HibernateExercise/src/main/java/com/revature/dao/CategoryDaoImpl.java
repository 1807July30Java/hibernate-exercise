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
		List<Category> categories = null;
		
		try {
			categories = s.createQuery("from Category").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		sf.close();
		
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
		Session s = sf.openSession();
		Category category = null;
		
		try {
			category = (Category) s.get(Category.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		sf.close();
		
		return category;
	}

	@Override
	public int addCategory(Category c) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		int categoryId = 0;
		
		try {
			categoryId = (int) s.save(c);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		sf.close();
		
		return categoryId;
	}

	@Override
	public void updateCategory(Category c) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.merge(c);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		sf.close();
	}

	@Override
	public void deleteCategory(Category c) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.delete(c);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		sf.close();
	}

}
