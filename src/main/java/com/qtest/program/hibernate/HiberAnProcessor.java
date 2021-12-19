package com.qtest.program.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

public class HiberAnProcessor {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        try {
            // default param for configure() is hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable throwable) {
            System.err.println("Failed to create sessionFactory object. " + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
        HiberAnProcessor hiberProcessor = new HiberAnProcessor();

        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        int qhaRowId1 = hiberProcessor.addQhaRow(1, "name1", timestamp, "note1");
        timestamp = new Timestamp(new java.util.Date().getTime());
        int qhaRowId2 = hiberProcessor.addQhaRow(2, "name2", timestamp, "note2");

        hiberProcessor.listQhaRows();
        hiberProcessor.updateQhaRow(qhaRowId1, "note1-1");
        hiberProcessor.listQhaRows();
        hiberProcessor.updateQhaRow(qhaRowId2, "note2-2");
        hiberProcessor.listQhaRows();
        hiberProcessor.deleteQhaRow(qhaRowId2);
        hiberProcessor.listQhaRows();
        hiberProcessor.deleteQhaRow(qhaRowId1);
        hiberProcessor.listQhaRows();
    }

    private void listQhaRows() {
        System.out.println("listing qha...");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            //Will get a Raw use of parameterized class or an Unchecked Assignment warning here
            //List qhaRows = session.createQuery("FROM QhaRow").list();
            // FROM a class name, not table name
            TypedQuery<QhaRow> query = session.createQuery("FROM QhaRow", QhaRow.class);
            List<QhaRow> qhaRows = query.getResultList();

            for (QhaRow qhaRow : qhaRows) {
                System.out.println("name: " + qhaRow.getName() +
                        " timestamp: " + qhaRow.getTimestamp() +
                        " note: " + qhaRow.getNote());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private int addQhaRow(int id, String name, Timestamp timestamp, String note) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int qhaRowId = 0;
        try {
            tx = session.beginTransaction();
            QhaRow qhaRow = new QhaRow(id, name, timestamp, note);
            qhaRowId = (int) session.save(qhaRow);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return qhaRowId;
    }

    private void updateQhaRow(int qhaRowId, String note) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            QhaRow qhaRow = session.get(QhaRow.class, qhaRowId);
            qhaRow.setNote(note);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    private void deleteQhaRow(int qhaRowId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            QhaRow qhaRow = session.get(QhaRow.class, qhaRowId);
            session.delete(qhaRow);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
