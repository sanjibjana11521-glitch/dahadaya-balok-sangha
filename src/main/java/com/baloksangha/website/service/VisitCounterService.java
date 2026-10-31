package com.baloksangha.website.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.baloksangha.website.entity.VisitCounter;

@Service
public class VisitCounterService {

    private final SessionFactory sessionFactory;

    public VisitCounterService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void registerVisitAndGetTotal() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.createNativeMutationQuery(
                    "INSERT INTO site_visit_counter (id, total_visits) VALUES (1, 1) " +
                    "ON CONFLICT (id) DO UPDATE SET total_visits = " +
                    "site_visit_counter.total_visits + 1"
                ).executeUpdate();

                transaction.commit();

            } catch (RuntimeException exception) {
                transaction.rollback();
                throw exception;
            }
        }
    }

    public long getTotalVisits() {
        try (Session session = sessionFactory.openSession()) {
            VisitCounter counter = session.get(VisitCounter.class, 1L);

            if (counter == null) {
                return 0;
            }

            return counter.getTotalVisits();
        }
    }
}
