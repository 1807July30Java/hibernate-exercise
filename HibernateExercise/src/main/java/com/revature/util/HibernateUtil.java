package com.revature.util;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() { //looks for hibernate.cfg.xml by default
		if (HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure();
			System.out.println("URL" + System.getenv("DBURL"));
			c.setProperty("hibernate.connection.username", System.getenv("DBUSER"));
			c.setProperty("hibernate.connection.password", System.getenv("DBPASS"));
			c.setProperty("hibernate.connection.url", System.getenv("DBURL"));
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
					//new MetadataSources(sr).buildMetadata().buildSessionFactory();
		}
		return HibernateUtil.sessionFactory;
	}
}