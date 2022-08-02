package report.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import report.model.Note;

import java.util.List;

@FeignClient(name = "ms-note", url ="localhost:8082")
public interface NoteProxy {

    @RequestMapping("/api/notes/")
    public List<Note> getAllNotes();

    @RequestMapping("/api/notes/{patientId}/notes/list")
    public List<Note> getPatientNotes(@PathVariable String patientId);

    @RequestMapping("/api/notes/{id}")
    public Note getNote(@PathVariable String id);

    @RequestMapping("/api/notes/update/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note);

    @PostMapping("/api/notes/delete/{id}")
    public Note deleteNote(@PathVariable String id);

    @PostMapping("/api/notes/add")
    public Note addNote(@RequestParam String patId, @RequestParam String recommendations);
}
