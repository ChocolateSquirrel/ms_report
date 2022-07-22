package report.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import report.model.Note;

import java.util.List;

@FeignClient(name = "ms-note", url ="localhost:8082")
public interface NoteProxy {

    @RequestMapping("/api/notes/")
    public List<Note> getAllNotes();

    @GetMapping("/api/notes/{patientId}/notes/list")
    public List<Note> getPatientNotes(@PathVariable String patientId);
}
