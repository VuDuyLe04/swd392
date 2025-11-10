package com.he187349.mvc.service;

import com.he187349.mvc.model.SalaryHistory;
import com.he187349.mvc.model.SalaryStatus;
import com.he187349.mvc.model.Worker;
import com.he187349.mvc.repository.SalaryHistoryRepository;
import com.he187349.mvc.repository.WorkerRepository;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class WorkerService {

    private final EntityManager em;
    private final WorkerRepository workerRepo;
    private final SalaryHistoryRepository historyRepo;

    public WorkerService(EntityManager em) {
        this.em = em;
        this.workerRepo = new WorkerRepository(em);
        this.historyRepo = new SalaryHistoryRepository(em);
    }

    public boolean addWorker(Worker worker) throws Exception {
        if (worker.getCode() == null || worker.getCode().isBlank()) {
            throw new Exception("Code cannot be empty");
        }
        if (workerRepo.existsByCode(worker.getCode())) {
            throw new Exception("Code is duplicated");
        }
        if (worker.getAge() < 18 || worker.getAge() > 50) {
            throw new Exception("Age must be between 18 and 50");
        }
        if (worker.getSalary() <= 0) {
            throw new Exception("Salary must be greater than 0");
        }

        em.getTransaction().begin();
        try {
            workerRepo.save(worker);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public boolean changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be > 0");
        }

        Worker worker = workerRepo.findByCode(code);
        if (worker == null) {
            throw new Exception("Worker code not found");
        }

        em.getTransaction().begin();
        try {
            double current = worker.getSalary();
            double newSalary;

            if (status == SalaryStatus.UP) {
                newSalary = current + amount;
            } else {
                newSalary = current - amount;
                if (newSalary < 0) {
                    throw new Exception("New salary cannot be negative");
                }
            }

            worker.setSalary(newSalary);
            workerRepo.update(worker);

            SalaryHistory history = new SalaryHistory(
                    worker.getCode(),
                    worker.getName(),
                    worker.getAge(),
                    worker.getSalary(),
                    status,
                    LocalDateTime.now()
            );
            historyRepo.save(history);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<SalaryHistory> getInformationSalary() {
        return historyRepo.findAllOrderByWorkerCode();
    }
}
