package com.he187349.mvc;

import com.he187349.mvc.controller.WorkerController;
import com.he187349.mvc.service.WorkerService;
import com.he187349.mvc.view.ConsoleView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("workerPU");
        EntityManager em = emf.createEntityManager();

        WorkerService service = new WorkerService(em);
        ConsoleView view = new ConsoleView();
        WorkerController controller = new WorkerController(service, view);

        controller.start();

        em.close();
        emf.close();
    }
}
