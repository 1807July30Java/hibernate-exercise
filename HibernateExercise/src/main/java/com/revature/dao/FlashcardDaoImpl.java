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
		List<Flashcard> fla = null;
		
		try {
			fla = s.createQuery("from Flashcard").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fla;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> getActiveFlashcards() {
		Session s = sf.openSession();
		List<Flashcard> fla = null;
		
		try {
			Query q = s.createQuery("from Flashcard where category in (from Category where isActive = :status");
			q.setParameter("status", "ACTIVE");
			fla = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fla;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flashcard> filterCardsByQuestion(String text) {
		Session s = sf.openSession();
		Criteria cri = s.createCriteria(Flashcard.class);
		cri.add(Restrictions.like("question", text));
		List<Flashcard> fla = null;
		
		try {
			fla = cri.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return fla;
	}

	@Override
	public Flashcard getFlashcardById(int id) {
		Session s = sf.openSession();
		Flashcard fla = null;
		
		try {
			fla = (Flashcard) s.get(Flashcard.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return fla;
	}

	@Override
	public void addFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		try {
			s.save(f);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		s.close();
		
	}

	@Override
	public void updateFlashcard(Flashcard f) {
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		
		try {
			s.merge(f);
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
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
