package com.revature.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Category;
import com.revature.beans.Flashcard;
import com.revature.dao.CategoryDao;
import com.revature.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Category.class);
		List cList = criteria.list();
		
		tr.commit();
		sessFact.close();
		
		return cList;
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Category.class);
		criteria.add(Restrictions.eq("CATEGORY_ID", id));
		Category cat = (Category) criteria.uniqueResult();
		
		tr.commit();
		sessFact.close();
		
		return cat;
	}

	@Override
	public int addCategory(Category c) {
		// TODO Auto-generated method stub
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.save(c);
		
		tr.commit();
		sessFact.close();
		return 1;
	}

	@Override
	public void updateCategory(Category c) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.update(c);
		
		tr.commit();
		sessFact.close();
	}

	@Override
	public void deleteCategory(Category c) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.delete(c);
		
		tr.commit();
		sessFact.close();		
	}

}
