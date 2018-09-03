package com.revature.dao;

import com.revature.beans.Category;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public List<Category> getCategories() {
        Session s = sf.openSession();
        List<Category> cl = new ArrayList<>();

        try {
            cl = s.createQuery("from Category").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cl;
    }

    @Override
    public Category getCategoryById(int id) {
        Session s = sf.openSession();
        Category c = null;

        try {
            c = (Category) s.get(Category.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public int addCategory(Category c) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        int pk = 0;

        try {
            pk = (int) s.save(c);

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        tx.commit();
        s.close();
        return pk;
    }

    @Override
    public void updateCategory(Category c) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            s.merge(c);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        s.close();
    }

    @Override
    public void deleteCategory(Category c) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            s.delete(c);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        s.close();
    }

    public void closeSF() {
        sf.close();
    }
}
