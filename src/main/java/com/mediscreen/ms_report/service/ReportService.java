package com.mediscreen.ms_report.service;

import com.mediscreen.ms_report.model.*;
import com.mediscreen.ms_report.proxy.NoteProxy;
import com.mediscreen.ms_report.proxy.PatientProxy;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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

    public Patient getPatient(String id) {
        return patientProxy.getPatient(Integer.parseInt(id));
    }

    public Patient addPatient(Patient patient) {
        return patientProxy.addPatient(patient);
    }

    public Patient updatePatient(String id, Patient patient){
        return patientProxy.updatePatientInfo(Integer.parseInt(id), patient);
    }

    public Patient deletePatient(String id){
        List<Note> notes = noteProxy.getPatientNotes(id);
        notes.forEach(n -> noteProxy.deleteNote(n.getId()));
        return patientProxy.delete(Integer.parseInt(id));
    }

    public List<Note> getAllNotes() {
        return noteProxy.getAllNotes();
    }

    public List<Note> getPatientNotes(String patientId){
        return noteProxy.getPatientNotes(patientId);
    }

    public Note getNote(String noteId) { return noteProxy.getNote(noteId); }

    public Note updateNote(String noteId, Note note){
        return noteProxy.updateNote(noteId, note);
    }

    public Note addNote(String patientId, String recommendations){
        return noteProxy.addNote(patientId, recommendations);
    }

    public Note deleteNote(String noteId){
        return noteProxy.deleteNote(noteId);
    }

    public Report generateReport(String patientId){
        Patient patient = patientProxy.getPatient(Integer.parseInt(patientId));
        List<Note> notes = noteProxy.getPatientNotes(patientId);

        long nbTriggers = getNbTriggers(notes);
        LevelRisk level = getLevel(patient.getAge(), nbTriggers, patient.getSex());

        return new Report(patient, patient.getAge(), level);
    }

    public long getNbTriggers(List<Note> notes){
        String allRecommendations = String.valueOf(notes.stream().map(Note::getRecommendations)
                .reduce((r1, r2) -> r1.concat(" ").concat(r2))).toLowerCase(Locale.ROOT);
        return Arrays.stream(Trigger.values())
                .filter(t -> allRecommendations.contains(t.getFrench()) || allRecommendations.contains(t.getEnglish()))
                .count();
    }

    public LevelRisk getLevel(int age, long nbTriggers, Sex sex){
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
