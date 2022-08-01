package report.service;

import org.springframework.stereotype.Service;
import report.model.*;
import report.proxy.NoteProxy;
import report.proxy.PatientProxy;

import java.util.List;

@Service
public class ReportService {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;

    public ReportService(PatientProxy patientProxy, NoteProxy noteProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
    }

    public List<Patient> getAllPatients() {
        return patientProxy.getAllPatients();
    }

    public List<Note> getAllNotes() {
        return noteProxy.getAllNotes();
    }

    public List<Note> getPatientNotes(String patientId){
        return noteProxy.getPatientNotes(patientId);
    }

    public Report generateReport(String patientId){
        Patient patient = patientProxy.getPatient(Integer.parseInt(patientId));
        List<Note> notes = noteProxy.getPatientNotes(patientId);

        int nbTriggers = getNbTriggers(notes);
        LevelRisk level = getLevel(patient.getAge(), nbTriggers, patient.getSex());

        return new Report(patient, level);
    }

    public int getNbTriggers(List<Note> notes){
        return 3; // a voir comment faire pour récupérer fume, fumeur...
    }

    public LevelRisk getLevel(int age, int nbTriggers, Sex sex){
        LevelRisk level = LevelRisk.NONE;

        if (nbTriggers == 2 && age > 30) level = LevelRisk.BORDERLINE;

        // Case "in Danger"
        if (age < 30){
            if (nbTriggers == 3 && sex.equals(Sex.MALE))  level = LevelRisk.IN_DANGER;
            if (nbTriggers == 4 && sex.equals(Sex.FEMALE))  level = LevelRisk.IN_DANGER;
        }
        if (age > 30 && nbTriggers == 6)  level = LevelRisk.IN_DANGER;

        // Case "Early onset"
        if (age < 30){
            if (nbTriggers == 5 && sex.equals(Sex.MALE))  level = LevelRisk.EARLY_ONSET;
            if (nbTriggers == 7 && sex.equals(Sex.FEMALE))  level = LevelRisk.EARLY_ONSET;
        }
        if (age > 30 && nbTriggers >= 8)  level = LevelRisk.EARLY_ONSET;

        return level;
    }
}
