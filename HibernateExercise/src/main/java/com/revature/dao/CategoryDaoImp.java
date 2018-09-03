package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.util.HibernateUtil;

public class CategoryDaoImp implements CategoryDao {

	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		List<Category> lc = null;
		try{
			Session s = sf.openSession();
			Query q = s.createQuery("from Category");
			lc = (List<Category>) q.list();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lc;
	}

	@Override
	public Category getCategoryById(int id) {
		Category c = null;
		try {
			Session s = sf.openSession();
			c = (Category) s.get(Category.class, id);
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public int addCategory(Category c) {
		Session s = sf.openSession();
		try {
			
			Transaction tx = s.beginTransaction();
			s.persist(c);
			tx.commit();
			s.close();
			return c.getId();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();
		return 0;
	}

	@Override
	public void updateCategory(Category c) {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.merge(c);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();

	}

	@Override
	public void deleteCategory(Category c) {
		// TODO Auto-generated method stub
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.persist(c);
			s.delete(c);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();
	}

}
