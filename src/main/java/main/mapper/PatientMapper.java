package main.mapper;

import main.model.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {

    List<Patient> selectAllPatients();

    void insertPatient(Patient patient);

    void deletePatient(Integer id);

    void updatePatient(Patient patient);

    Patient selectPatientById(Integer id);
}
