package report.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import report.model.Patient;

import java.util.List;

@FeignClient (name = "ms-patient", url = "localhost:8081")
public interface PatientProxy {

    @RequestMapping("/api/patients/")
    public List<Patient> getAllPatients();

    @RequestMapping("/api/patients/{id}")
    public Patient getPatient(@PathVariable(value = "id") int patientId);

    @RequestMapping("/api/patients/")
    public Patient addPatient(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String dob, @RequestParam String sex,
                              @RequestParam String address, @RequestParam String phone);
}
