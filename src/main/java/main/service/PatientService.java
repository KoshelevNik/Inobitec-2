package main.service;

import main.mapper.PatientMapper;
import main.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    private PatientMapper patientMapper;

    public void create(Patient patient) {
        patientMapper.insert(UUID.fromString(patient.getId()), patient.getName(), patient.getPassword(), patient.getMail());
    }

    public List<Patient> read() {
        return patientMapper.selectAll();
    }

    public void update(Patient patient) {
        patientMapper.update(UUID.fromString(patient.getId()), patient.getName(), patient.getPassword(), patient.getMail());
    }

    public void delete(Patient patient) {
        patientMapper.delete(UUID.fromString(patient.getId()));
    }

    public Patient read(UUID uuid) {
        return patientMapper.selectById(uuid);
    }
}
