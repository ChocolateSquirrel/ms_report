package com.mediscreen.ms_report.model;

import lombok.Data;

@Data
public class Report {
    private Patient patient;
    private int age;
    private LevelRisk level;

    public Report() {}

    public Report(Patient patient, int age, LevelRisk level) {
        this.patient = patient;
        this.age = age;
        this.level = level;
    }
}
