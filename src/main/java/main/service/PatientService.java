package main.service;

import main.model.Patient;
import main.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public void createPatient(Patient patient) {
        patientRepository.insertPatient(patient);
    }

    public List<Patient> readAllPatients() {
        return patientRepository.selectAllPatients();
    }

    public void updatePatient(Patient patient, Integer id) {
        patient.setId(id);
        patientRepository.updatePatient(patient);
    }

    public void deletePatient(Integer id) {
        patientRepository.deletePatient(id);
    }

    public Patient readPatientById(Integer id) {
        return patientRepository.selectPatientById(id);
    }
}
