package report.service;

import org.springframework.stereotype.Service;
import report.model.Patient;
import report.proxy.PatientProxy;

import java.util.List;

@Service
public class ReportService {

    private final PatientProxy patientProxy;

    public ReportService(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    public List<Patient> getAllPatients() {
        return patientProxy.getAllPatients();
    }
}
