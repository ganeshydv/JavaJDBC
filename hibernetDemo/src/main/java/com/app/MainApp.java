package com.app;

import com.db.HibernateUtil;
import com.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        // Get SessionFactory
//        SessionFactory factory= HibernateUtil.getSessionFactory();

        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("DB Session : start ...");
        // Begin Transaction
        Transaction transaction = session.beginTransaction();
        System.out.println("transaction : start ...");
        // Create and Save User
//        User user = new User();
//        user.setUsername("JohnDoe");
//        user.setEmail("john.doe@example.com");
//        session.save(user);

        // Commit Transaction
        transaction.commit();
        System.out.println("Transaction : end ....");

        // Close Session
        session.close();
        System.out.println("Session : Close ...");
        HibernateUtil.getSessionFactory().close();
    }
}
