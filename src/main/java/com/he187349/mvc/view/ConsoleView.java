package com.he187349.mvc.view;

import com.he187349.mvc.model.SalaryHistory;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public int showMenuAndGetChoice() {
        System.out.println("======== Worker Management =========");
        System.out.println("1. Add Worker");
        System.out.println("2. Up salary");
        System.out.println("3. Down salary");
        System.out.println("4. Display Information salary");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
        String line = scanner.nextLine();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String input(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showSalaryHistory(List<SalaryHistory> list) {
        System.out.println("------------- Display Information Salary -------------");
        System.out.printf("%-10s %-15s %-5s %-12s %-8s %-20s%n",
                "Code", "Name", "Age", "Salary", "Status", "Date");
        for (SalaryHistory h : list) {
            System.out.printf("%-10s %-15s %-5d %-12.2f %-8s %-20s%n",
                    h.getWorkerCode(),
                    h.getWorkerName(),
                    h.getAge(),
                    h.getSalaryAfter(),
                    h.getStatus(),
                    h.getChangeDate().format(fmt));
        }
    }
}
