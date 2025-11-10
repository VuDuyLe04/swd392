package com.he187349.mvc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "salary_history")
public class SalaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "worker_code", length = 50, nullable = false)
    private String workerCode;

    @Column(name = "worker_name", length = 100, nullable = false)
    private String workerName;

    @Column(nullable = false)
    private int age;

    @Column(name = "salary_after", nullable = false)
    private double salaryAfter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SalaryStatus status;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    public SalaryHistory() {
    }

    public SalaryHistory(String workerCode, String workerName, int age,
                         double salaryAfter, SalaryStatus status, LocalDateTime changeDate) {
        this.workerCode = workerCode;
        this.workerName = workerName;
        this.age = age;
        this.salaryAfter = salaryAfter;
        this.status = status;
        this.changeDate = changeDate;
    }

    public Long getId() { return id; }

    public String getWorkerCode() { return workerCode; }

    public String getWorkerName() { return workerName; }

    public int getAge() { return age; }

    public double getSalaryAfter() { return salaryAfter; }

    public SalaryStatus getStatus() { return status; }

    public LocalDateTime getChangeDate() { return changeDate; }
}
