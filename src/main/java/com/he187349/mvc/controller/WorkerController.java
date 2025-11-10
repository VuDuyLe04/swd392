package com.he187349.mvc.controller;

import com.he187349.mvc.model.SalaryStatus;
import com.he187349.mvc.model.Worker;
import com.he187349.mvc.service.WorkerService;
import com.he187349.mvc.view.ConsoleView;

public class WorkerController {

    private final WorkerService service;
    private final ConsoleView view;

    public WorkerController(WorkerService service, ConsoleView view) {
        this.service = service;
        this.view = view;
    }

    public void start() {
        while (true) {
            int choice = view.showMenuAndGetChoice();
            switch (choice) {
                case 1 -> addWorker();
                case 2 -> changeSalary(SalaryStatus.UP);
                case 3 -> changeSalary(SalaryStatus.DOWN);
                case 4 -> displaySalaryInfo();
                case 5 -> {
                    view.showMessage("Exiting...");
                    return;
                }
                default -> view.showMessage("Invalid choice. Try again.");
            }
        }
    }

    private void addWorker() {
        view.showMessage("--------- Add Worker ----------");
        try {
            String code = view.input("Enter Code");
            String name = view.input("Enter Name");
            int age = Integer.parseInt(view.input("Enter Age"));
            double salary = Double.parseDouble(view.input("Enter Salary"));
            String location = view.input("Enter Work Location");

            Worker worker = new Worker(code, name, age, salary, location);
            service.addWorker(worker);
            view.showMessage("Add worker successfully.");
        } catch (NumberFormatException e) {
            view.showMessage("Invalid number format.");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void changeSalary(SalaryStatus status) {
        view.showMessage("------- " + (status == SalaryStatus.UP ? "Up" : "Down") + " Salary --------");
        try {
            String code = view.input("Enter Code");
            double amount = Double.parseDouble(view.input("Enter Salary Change Amount"));
            service.changeSalary(status, code, amount);
            view.showMessage("Salary updated.");
        } catch (NumberFormatException e) {
            view.showMessage("Invalid number format.");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void displaySalaryInfo() {
        var list = service.getInformationSalary();
        if (list.isEmpty()) {
            view.showMessage("No salary changes yet.");
        } else {
            view.showSalaryHistory(list);
        }
    }
}
