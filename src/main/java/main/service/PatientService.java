package main.service;

import main.model.Patient;
import main.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public boolean updatePatient(Patient patient, Integer id) {
        if (Objects.equals(patient.getId(), id)) {
            boolean idExist = false;
            for (Patient p : readAllPatients()) {
                if (Objects.equals(p.getId(), id)) {
                    idExist = true;
                    break;
                }
            }
            if (!idExist) return false;
            patientRepository.updatePatient(patient);
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePatient(Integer id) {
        boolean idExist = false;
        for (Patient p : readAllPatients()) {
            if (Objects.equals(p.getId(), id)) {
                idExist = true;
                break;
            }
        }
        if (!idExist) return false;
        patientRepository.deletePatient(id);
        return true;
    }

    public Patient readPatientById(Integer id) {
        return patientRepository.selectPatientById(id);
    }
}
