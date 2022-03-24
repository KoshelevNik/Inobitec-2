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

    public void create(Patient patient) {
        patientRepository.insert(patient);
    }

    public List<Patient> read() {
        return patientRepository.selectAll();
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient.getId());
    }

    public Patient read(Integer id) {
        return patientRepository.selectById(id);
    }
}
