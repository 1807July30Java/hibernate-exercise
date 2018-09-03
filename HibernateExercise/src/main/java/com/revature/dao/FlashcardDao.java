package com.revature.dao;

import java.util.List;

import com.revature.beans.Flashcard;

public interface FlashcardDao {
	
	public List<Flashcard> getFlashcards();
	
	/*
	 * Retrieves all Flashcards belonging to ACTIVE categories. 
	 * Use a Query to perform this operation.
	 */
	public List<Flashcard> getActiveFlashcards();
	
	/*
	 * Retrieves all Flashcards with the provided string in their
	 * question text.
	 * Use Criteria to perform this operation. 
	 */
	public List<Flashcard> filterCardsByQuestion(String text);
	
	public Flashcard getFlashcardById(int id);

	public void addFlashcard(Flashcard f);
	
	public void updateFlashcard(Flashcard f);

	public void deleteFlashcard(Flashcard f);

}
