package main.repository;

import main.mapper.PatientMapper;
import main.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {

    @Autowired
    private PatientMapper patientMapper;

    public void insertPatient(Patient patient) {
        patientMapper.insertPatient(patient);
    }

    public void deletePatient(Integer id) {
        patientMapper.deletePatient(id);
    }

    public void updatePatient(Patient patient) {
        patientMapper.updatePatient(patient);
    }

    public List<Patient> selectAllPatients() {
        return patientMapper.selectAllPatients();
    }

    public Patient selectPatientById(Integer id) {
        return patientMapper.selectPatientById(id);
    }
}
