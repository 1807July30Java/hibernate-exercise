package com.revature.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Category;
import com.revature.beans.CategoryStatus;
import com.revature.beans.Flashcard;
import com.revature.dao.FlashcardDao;
import com.revature.util.HibernateUtil;

public class FlashcardDaoImpl implements FlashcardDao {

	@Override
	public List<Flashcard> getFlashcards() {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Flashcard.class);
		List fList = criteria.list();
		
		tr.commit();
		sessFact.close();
		
		
		return fList;
	}

	@Override
	public List<Flashcard> getActiveFlashcards() {
		// TODO Auto-generated method stub
		
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Category.class);
		criteria.add(Restrictions.eq("Category", CategoryStatus.ACTIVE));
		List fList = criteria.list();
		
		tr.commit();
		sessFact.close();
		
		return fList;
	}

	@Override
	public List<Flashcard> filterCardsByQuestion(String text) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Category.class);
		criteria.add(Restrictions.eq("QUESTION", text));
		List fList = criteria.list();
		
		tr.commit();
		sessFact.close();
		
		return fList;
	}

	@Override
	public Flashcard getFlashcardById(int id) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		Criteria criteria = session.createCriteria(Flashcard.class);
		criteria.add(Restrictions.eq("FLASHCARD_ID", id));
		Flashcard fcard = (Flashcard) criteria.uniqueResult();
		
		tr.commit();
		sessFact.close();
		
		return fcard;
	}

	@Override
	public void addFlashcard(Flashcard f) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.save(f);
		
		tr.commit();
		sessFact.close();
	}

	@Override
	public void updateFlashcard(Flashcard f) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.update(f);
		
		tr.commit();
		sessFact.close();
		
	}

	@Override
	public void deleteFlashcard(Flashcard f) {
		// TODO Auto-generated method stub
		SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		Transaction tr = session.beginTransaction();

		session.delete(f);
		
		tr.commit();
		sessFact.close();	
	}

}
