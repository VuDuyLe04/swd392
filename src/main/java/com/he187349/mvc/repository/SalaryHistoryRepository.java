package com.he187349.mvc.repository;

import com.he187349.mvc.model.SalaryHistory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SalaryHistoryRepository {

    private final EntityManager em;

    public SalaryHistoryRepository(EntityManager em) {
        this.em = em;
    }

    public void save(SalaryHistory history) {
        em.persist(history);
    }

    public List<SalaryHistory> findAllOrderByWorkerCode() {
        return em.createQuery(
                "SELECT s FROM SalaryHistory s ORDER BY s.worker.code, s.changeDate",
                SalaryHistory.class)
                .getResultList();
    }
}
