package com.revature.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Category;
import com.revature.beans.CategoryStatus;
import com.revature.dao.CategoryDao;
import com.revature.dao.CategoryDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		
		CategoryDao c = new CategoryDaoImpl();
		
		c.deleteCategory(new Category(5, "Music", CategoryStatus.INACTIVE));
	}
	
	
}
