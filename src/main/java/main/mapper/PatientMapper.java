package main.mapper;

import main.model.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {

    List<Patient> selectAll();

    void insert(Patient patient);

    void delete(Integer id);

    void update(Patient patient);

    Patient selectById(Integer id);
}
