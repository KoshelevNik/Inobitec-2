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

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Integer id) {
        return patientService.readPatientById(id);
    }

    @GetMapping
    public List<Patient> getAllPatient() {
        return patientService.readAllPatients();
    }

    @PostMapping
    public Patient createNewPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
        return patient;
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/{id}")
    public void patientPUT(@PathVariable Integer id, @RequestBody Patient patient) {
        patientService.updatePatient(patient, id);
    }
}
