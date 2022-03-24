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

    public void insert(Patient patient) {
        patientMapper.insert(patient);
    }

    public void delete(Integer id) {
        patientMapper.delete(id);
    }

    public void update(Patient patient) {
        patientMapper.update(patient);
    }

    public List<Patient> selectAll() {
        return patientMapper.selectAll();
    }

    public Patient selectById(Integer id) {
        return patientMapper.selectById(id);
    }
}
