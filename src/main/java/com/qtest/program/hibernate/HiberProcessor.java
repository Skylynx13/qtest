package com.qtest.program.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class HiberProcessor {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        try {
            // default param for configure() is hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable throwable) {
            System.err.println("Failed to create sessionFactory object. " + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
        HiberProcessor hiberProcessor = new HiberProcessor();

        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        int qhRowId1 = hiberProcessor.addQhRow(1, "name1", timestamp, "note1");
        timestamp = new Timestamp(new java.util.Date().getTime());
        int qhRowId2 = hiberProcessor.addQhRow(2, "name2", timestamp, "note2");

        hiberProcessor.listQhRows();
        hiberProcessor.updateQhRow(qhRowId1, "note1-1");
        hiberProcessor.listQhRows();
        hiberProcessor.updateQhRow(qhRowId2, "note2-2");
        hiberProcessor.listQhRows();
        hiberProcessor.deleteQhRow(qhRowId2);
        hiberProcessor.listQhRows();
        hiberProcessor.deleteQhRow(qhRowId1);
        hiberProcessor.listQhRows();
    }

    private void listQhRows() {
        System.out.println("listing...");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            //Will get a Raw use of parameterized class or an Unchecked Assignment warning here
            //List qhRows = session.createQuery("FROM QhRow").list();
            // FROM a class name, not table name
            TypedQuery<QhRow> query = session.createQuery("FROM QhRow", QhRow.class);
            List<QhRow> qhRows = query.getResultList();

            for (QhRow qhRow : qhRows) {
                System.out.println("name: " + qhRow.getName() +
                        " timestamp: " + qhRow.getTimestamp() +
                        " note: " + qhRow.getNote());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private int addQhRow(int id, String name, Timestamp timestamp, String note) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int qhRowId = 0;
        try {
            tx = session.beginTransaction();
            QhRow qhRow = new QhRow(id, name, timestamp, note);
            qhRowId = (int) session.save(qhRow);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return qhRowId;
    }

    private void updateQhRow(int qhRowId, String note) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            QhRow qhRow = session.get(QhRow.class, qhRowId);
            qhRow.setNote(note);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private void deleteQhRow(int qhRowId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            QhRow qhRow = session.get(QhRow.class, qhRowId);
            session.delete(qhRow);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
