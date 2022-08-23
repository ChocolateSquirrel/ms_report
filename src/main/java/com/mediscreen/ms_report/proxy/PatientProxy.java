package com.mediscreen.ms_report.proxy;

import com.mediscreen.ms_report.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient (name = "ms-patient", url = "ms-patient:8081")
public interface PatientProxy {

    @RequestMapping("/api/patients/")
    public List<Patient> getAllPatients();

    @RequestMapping("/api/patients/{id}")
    public Patient getPatient(@PathVariable(value = "id") int patientId);

    @PostMapping("/api/patients/add")
    public Patient addPatient(@RequestBody Patient patient);

    @RequestMapping("/api/patients/update/{id}")
    public Patient updatePatientInfo(@PathVariable(value = "id") int patientId, @RequestBody Patient patient);

    @RequestMapping("/api/patients/delete/{id}")
    public Patient delete(@PathVariable(value = "id") int patientId);
}
