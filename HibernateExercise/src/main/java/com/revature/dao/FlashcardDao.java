package com.revature.dao;

import org.hibernate.SessionFactory;

import com.revature.pojo.Category;
import com.revature.pojo.Flashcard;

public interface FlashcardDAO  {
	public int newFlashcard(SessionFactory sf,String question,String answer,Category C);
	public Flashcard getFlashcardById(SessionFactory sf,int flashcardid);
}
