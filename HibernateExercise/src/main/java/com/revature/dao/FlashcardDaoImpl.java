package com.revature.dao;

import com.revature.beans.CategoryStatus;
import com.revature.beans.Flashcard;
import com.revature.util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class FlashcardDaoImpl implements FlashcardDao {

    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public List<Flashcard> getFlashcards() {
        Session s = sf.openSession();
        List<Flashcard> fl = new ArrayList<>();

        try {
            fl = s.createQuery("from Flashcard").list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        s.close();
        return fl;
    }

    @Override
    public List<Flashcard> getActiveFlashcards() {
        Session s = sf.openSession();
        List<Flashcard> fl = new ArrayList<>();

        try {
            Query q = s.createQuery("from Flashcard where category in (from Category where isActive = :status");
            q.setParameter("status", CategoryStatus.ACTIVE);
            fl = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        s.close();
        return fl;
    }

    @Override
    public List<Flashcard> filterCardsByQuestion(String text) {
        Session s = sf.openSession();
        List<Flashcard> fl = new ArrayList<>();

        try {
            Criteria c = s.createCriteria(Flashcard.class);
            c.add(Restrictions.eq("question", text));
            fl = c.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        s.close();
        return fl;
    }

    @Override
    public Flashcard getFlashcardById(int id) {
        Session s = sf.openSession();
        Flashcard f = null;

        try {
            Criteria c = s.createCriteria(Flashcard.class);
            c.add(Restrictions.eq("id", id));
            f = (Flashcard) c.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        s.close();
        return f;
    }

    @Override
    public void addFlashcard(Flashcard f) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            s.persist(f);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        s.close();
    }

    @Override
    public void updateFlashcard(Flashcard f) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            s.merge(f);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }

        s.close();
    }

    @Override
    public void deleteFlashcard(Flashcard f) {
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        try {
            s.delete(f);
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
