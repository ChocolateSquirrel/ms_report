package report.model;

import lombok.Data;

@Data
public class Report {
    private Patient patient;
    private LevelRisk level;

    public Report() {}

    public Report(Patient patient, LevelRisk level) {
        this.patient = patient;
        this.level = level;
    }
}
