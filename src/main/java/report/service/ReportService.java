package report.service;

import org.springframework.stereotype.Service;
import report.model.Note;
import report.model.Patient;
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
}
