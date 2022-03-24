package main.controller;

import main.model.Patient;
import main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/byId")
    public Patient patientGETById(@RequestParam Integer id) {
        return patientService.read(id);
    }

    @GetMapping("/all")
    public List<Patient> patientGETAll() {
        return patientService.read();
    }

    @PostMapping("/create")
    public Patient patientPOST(@RequestBody Patient patient) {
        patientService.create(patient);
        return patient;
    }

    @DeleteMapping("/delete")
    public Patient patientDELETE(@RequestBody Patient patient) {
        patientService.delete(patient);
        return patient;
    }

    @PutMapping("/update")
    public Patient patientPUT(@RequestBody Patient patient) {
        patientService.update(patient);
        return patient;
    }
}
