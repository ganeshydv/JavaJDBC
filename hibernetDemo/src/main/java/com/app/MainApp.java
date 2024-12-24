package com.app;

import com.db.HibernateUtil;
import com.userB.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        // Get SessionFactory
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Begin Transaction
        Transaction transaction = session.beginTransaction();

        // Create and Save User
        User user = new User();
        user.setUsername("JohnDoe");
        user.setEmail("john.doe@example.com");
        session.save(user);

        // Commit Transaction
        transaction.commit();

        // Close Session
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
