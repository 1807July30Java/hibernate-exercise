package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.pojo.Category;
import com.revature.pojo.Flashcard;

public class FlashcardDAOImpl implements FlashcardDAO{
	public int newFlashcard(SessionFactory sf,String question,String answer, Category c) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Flashcard f = new Flashcard(question,answer,c);
		int newid = (int) s.save(f);
		tx.commit();
		s.close();
		return newid;
	}
	public Flashcard getFlashcardById(SessionFactory sf,int flashcardid) {
		Session s = sf.openSession();
		Flashcard f = (Flashcard) s.load(Flashcard.class, flashcardid);
		System.out.println(f.getQuestion());
		s.close();
		return null;
	}
}
