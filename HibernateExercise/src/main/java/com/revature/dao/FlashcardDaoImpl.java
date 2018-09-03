package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Category;
import com.revature.beans.Flashcard;
import com.revature.util.HibernateUtil;

public class FlashcardDaoImpl implements FlashcardDao {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();

	public FlashcardDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getFlashcards() {
		Session s = sf.openSession();
		List<Flashcard> fl = null;
		
		try {
			fl = s.createQuery("from Flashcard").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getActiveFlashcards() {
		Session s = sf.openSession();
		List<Flashcard> fl = null;
		
		try {
			Query q = s.createQuery("from Flashcard where category = :categoryVar"); 
			q.setParameter("categoryVar", s.createQuery("from Category where isActive = ACTIVE"));
			fl = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> filterCardsByQuestion(String text) {
		Session s = sf.openSession();
		Criteria cr = s.createCriteria(Flashcard.class);
		cr.add(Restrictions.like("question", text));
		List<Flashcard> fl = null;
		
		try {
			fl = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fl;
	}

	@Override
	public Flashcard getFlashcardById(int id) {
		Session s = sf.openSession();
		Flashcard f = null;
		
		try {
			f = (Flashcard) s.get(Flashcard.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		return f;
	}

	@Override
	public void addFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.save(f);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
		

	}

	@Override
	public void updateFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(f);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

	@Override
	public void deleteFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(f);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

}
