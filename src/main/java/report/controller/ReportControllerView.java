package report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import report.model.Note;
import report.model.Patient;
import report.model.Report;
import report.service.ReportService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/view")
public class ReportControllerView {

    private final ReportService reportService;

    public ReportControllerView(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String home(Model model){
        log.info("Request GET : /view/");
        List<Patient> patientsList = reportService.getAllPatients();
        model.addAttribute("patients", patientsList);
        log.info("Response for /view/ : there are " + patientsList.size() + " patients");
        return "home";
    }

    @GetMapping("/patients/{id}")
    public String getPatientInfo(@PathVariable String id, Model model){
        log.info("Request GET : /view/patients/" + id);
        Patient patient = reportService.getPatient(id);
        model.addAttribute("patient", patient);
        log.info("Response for /view/patients/" + id + " : " + patient.getFirstName() + " " + patient.getLastName());
        return "patient/show";
    }

    @GetMapping("/patients/add")
    public String showAddForm(Model model){
        log.info("Request GET : /view/patients/add");
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        log.info("Response for /view/patients/add : OK" );
        return "patient/add";
    }

    @PostMapping("/patients/add")
    public String addPatient(@Valid @ModelAttribute Patient patient, Model model, BindingResult result){
        log.info("Request POST : /view/patients/add");
        if (!result.hasErrors()){
            Patient patientToAdd = reportService.addPatient(patient);
            List<Patient> patientsList = reportService.getAllPatients();
            model.addAttribute("patients", patientsList);
            return "home";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "home";
    }

    @GetMapping("/patients/update/{id}")
    public String showUpdateForm(@PathVariable(value = "id") String patientId, Model model){
        log.info("Request GET : /view/patients/update/" + patientId);
        Patient patient = reportService.getPatient(patientId);
        model.addAttribute("patient", patient);
        log.info("Response for /view/patients/update/" + patientId + " : " + patient.getFirstName() + " " + patient.getLastName() + " is going to update");
        return "patient/update";
    }

    @PostMapping("/patients/update/{id}")
    public String updatePatientInfo(@PathVariable(value = "id") String patientId, @Valid @ModelAttribute Patient patient, Model model, BindingResult result){
        log.info("Request POST : /view/patients/update/" + patientId);
        if (!result.hasErrors()){
            Patient patientUpdated = reportService.updatePatient(patientId, patient);
            List<Patient> patientsList = reportService.getAllPatients();
            model.addAttribute("patients", patientsList);
            return "home";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "patient/update";
    }

    @GetMapping("/report/{id}")
    public String showReport(@PathVariable String id, Model model){
        log.info("Request GET : /view/report/" + id);
        Report report = reportService.generateReport(id);
        model.addAttribute("report", report);
        return "report/show";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable String id, Model model){
        log.info("Request GET : /view/patients/delete/" + id);
        reportService.deletePatient(id);
        List<Patient> patientsList = reportService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return "home";
    }


    @GetMapping("/notes/{id}")
    public String getPatientNotes(@PathVariable String id, Model model) {
        log.info("Request GET : /view/notes/" + id);
        List<Note> notes = reportService.getPatientNotes(id);
        Patient patient = reportService.getPatient(id);
        model.addAttribute("notes", notes);
        model.addAttribute("patient", patient);
        log.info("Response for /view/notes/" + id + ": " + reportService.getPatient(id).getFirstName() + " " + reportService.getPatient(id).getLastName());
        return "note/show";
    }

    @GetMapping("/notes/add/{id}")
    public String showAddNote(@PathVariable(value = "id") String patientId, Model model){
        log.info("Request GET : /view/notes/add/" + patientId);
        Note note = new Note(patientId);
        Patient patient = reportService.getPatient(patientId);
        model.addAttribute("note", note);
        model.addAttribute("patient", patient);
        log.info("Response for /view/notes/add/" + patientId + ": OK");
        return "note/add";
    }

    @PostMapping("/notes/add/{id}")
    public String addNote(@PathVariable(value = "id") String patientId, @Valid @ModelAttribute Note note, Model model, BindingResult result) {
        log.info("Request POST : /view/notes/add");
        if (!result.hasErrors()){
            Note noteAdded = reportService.addNote(patientId, note.getRecommendations());
            List<Note> notes = reportService.getPatientNotes(patientId);
            model.addAttribute("notes", notes);
            return "note/show";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "note/add";
    }

    @GetMapping("/notes/update/{id}")
    public String showUpdateNote(@PathVariable String id, Model model) {
        log.info("Request GET : /view/notes/update/" + id);
        Note note = reportService.getNote(id);
        model.addAttribute("note", note);
        log.info("Response for /view/notes/update/" + id + ": OK");
        return "note/update";
    }

    @PostMapping("/notes/update/{id}")
    public String updateNote(@PathVariable String id, @Valid @ModelAttribute Note note, Model model, BindingResult result) {
        log.info("Request POST : /view/notes/update/" + id);
        if (!result.hasErrors()){
            Note noteUpdated = reportService.updateNote(id, note);
            List<Patient> patientsList = reportService.getAllPatients();
            model.addAttribute("patients", patientsList);
            return "home";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "note/update";
    }

    @GetMapping("/notes/delete/{id}")
    public String deleteNote(@PathVariable String id, Model model){
        log.info("Request GET : /view/notes/delete/" + id);
        reportService.deleteNote(id);
        List<Patient> patientsList = reportService.getAllPatients();
        model.addAttribute("patients", patientsList);
        return "home";
    }
}
