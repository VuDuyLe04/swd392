package com.he187349.mvc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "salary_history")
public class SalaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int age;

    @Column(name = "salary_after", nullable = false)
    private double salaryAfter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SalaryStatus status;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_code", referencedColumnName = "code")
    private Worker worker;

    public SalaryHistory() {
    }

    public SalaryHistory(Worker worker, int age,
            double salaryAfter, SalaryStatus status, LocalDateTime changeDate) {
        this.worker = worker;
        this.age = age;
        this.salaryAfter = salaryAfter;
        this.status = status;
        this.changeDate = changeDate;
    }

    public Worker getWorker() {
        return worker;
    }

    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalaryAfter() {
        return salaryAfter;
    }

    public void setSalaryAfter(double salaryAfter) {
        this.salaryAfter = salaryAfter;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }
}
