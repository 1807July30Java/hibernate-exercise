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

public class FlashCardDaoImp implements FlashcardDao {

	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getFlashcards() {
		List<Flashcard> lf = null;
		try{
			Session s = sf.openSession();
			Query q = s.createQuery("from Flashcard");
			lf = (List<Flashcard>) q.list();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getActiveFlashcards() {
		List<Flashcard> lf = null;
		try{
			Session s = sf.openSession();
			Query q = s.createQuery("from Flashcard where category = :categoryVar");
			q.setParameter("categoryVar", "ACTIVE");
			lf = (List<Flashcard>) q.list();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> filterCardsByQuestion(String text) {
		List<Flashcard> lf = null;
		try{
			Session s = sf.openSession();
			Criteria q = s.createCriteria(Flashcard.class);
			q.add(Restrictions.like("question", text));
			lf = (List<Flashcard>) q.list();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lf;
	}

	@Override
	public Flashcard getFlashcardById(int id) {
		Flashcard flash = null;
		try {
			Session s = sf.openSession();
			flash = (Flashcard) s.get(Flashcard.class, id);
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flash;
	}

	@Override
	public void addFlashcard(Flashcard f) {
		Session s = sf.openSession();
		try {
			
			Transaction tx = s.beginTransaction();
			s.persist(f);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();
	}

	@Override
	public void updateFlashcard(Flashcard f) {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.merge(f);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();
	}

	@Override
	public void deleteFlashcard(Flashcard f) {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.persist(f);
			s.delete(f);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
		s.close();
	}

}
