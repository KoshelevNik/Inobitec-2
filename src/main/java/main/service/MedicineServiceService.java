package main.service;

import main.mapper.MedicineServiceMapper;
import main.model.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineServiceService {

    @Autowired
    private MedicineServiceMapper medicineServiceMapper;

    public void create(MedicineService medicineService) {
        medicineServiceMapper.insert(
                medicineService.getName(),
                medicineService.getCost(),
                UUID.fromString(medicineService.getId()),
                medicineService.getDescription(),
                medicineService.getDoctorSpecialistName()
        );
    }

    public List<MedicineService> read() {
        return medicineServiceMapper.selectAll();
    }

    public void update(MedicineService medicineService) {
        medicineServiceMapper.update(
                medicineService.getName(),
                medicineService.getCost(),
                UUID.fromString(medicineService.getId()),
                medicineService.getDescription(),
                medicineService.getDoctorSpecialistName()
        );
    }

    public void delete(MedicineService medicineService) {
        medicineServiceMapper.delete(UUID.fromString(medicineService.getId()));
    }
}
