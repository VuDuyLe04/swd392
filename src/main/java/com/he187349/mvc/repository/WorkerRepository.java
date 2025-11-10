package com.he187349.mvc.repository;

import com.he187349.mvc.model.Worker;
import jakarta.persistence.EntityManager;

public class WorkerRepository {

    private final EntityManager em;

    public WorkerRepository(EntityManager em) {
        this.em = em;
    }

    public Worker findByCode(String code) {
        return em.find(Worker.class, code);
    }

    public boolean existsByCode(String code) {
        return findByCode(code) != null;
    }

    public void save(Worker worker) {
        em.persist(worker);
    }

    public void update(Worker worker) {
        em.merge(worker);
    }
}
