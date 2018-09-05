package com.revature.main;

import com.revature.dao.FlashcardDao;
import com.revature.dao.FlashcardDaoImpl;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FlashcardDao fc = new FlashcardDaoImpl();
		System.out.println(fc.getFlashcards());

	}

}
