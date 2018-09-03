package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Flashcard;
import com.revature.util.HibernateUtil;

public class FlashcardDaoImpl implements FlashcardDao {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getFlashcards() {
		Session s = sf.openSession();
		List<Flashcard> flashcards = null;
		
		try {
			flashcards = s.createQuery("from Flashcard").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return flashcards;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getActiveFlashcards() {
		Session s = sf.openSession();
		List<Flashcard> flashcards = null;
		
		try {
			Query q = s.createQuery("from Flashcard where category (from Category where isActive = :categoryStatus)");
			q.setParameter("categoryStatus", "ACTIVE");
			flashcards = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return flashcards;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> filterCardsByQuestion(String text) {
		Session s = sf.openSession();
		Criteria criteria = s.createCriteria(Flashcard.class);
		criteria.add(Restrictions.like("question", text));
		List<Flashcard> flashcards = null;
		
		try {
			flashcards = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return flashcards;
	}

	@Override
	public Flashcard getFlashcardById(int id) {
		Session s = sf.openSession();
		Flashcard flashcard = null;
		
		try {
			flashcard = (Flashcard) s.get(Flashcard.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flashcard;
	}

	@Override
	public void addFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.save(f);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		s.close();
		
	}

	@Override
	public void updateFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.merge(f);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		s.close();

	}

	@Override
	public void deleteFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.delete(f);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		s.close();
		
	}

}
