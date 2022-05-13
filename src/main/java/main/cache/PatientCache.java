package main.cache;

import main.model.Patient;

import java.util.Map;

public record PatientCache(Map<Integer, Patient> patientMap) {

    public Patient getPatientById(Integer id) {
        return patientMap.get(id);
    }

    public boolean containsPatientById(Integer id) {
        return patientMap.containsKey(id);
    }

    public boolean isEmpty() {
        return patientMap.isEmpty();
    }

    public Patient exist(Patient patient) {
        for (Map.Entry<Integer, Patient> v : patientMap.entrySet()) {
            Patient p = v.getValue();
            if (
                    p.getFirstName().equals(patient.getFirstName()) &&
                            p.getMidName().equals(patient.getMidName()) &&
                            p.getLastName().equals(patient.getLastName()) &&
                            p.getBirthday().equals(patient.getBirthday())
            ) {
                return p;
            }
        }
        return null;
    }
}
