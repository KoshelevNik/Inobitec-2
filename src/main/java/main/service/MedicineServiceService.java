package main.service;

import main.model.MedicineService;
import main.repository.MedicineServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceService {

    @Autowired
    private MedicineServiceRepository medicineServiceRepository;

    public void create(MedicineService medicineService) {
        medicineServiceRepository.insert(medicineService);
    }

    public List<MedicineService> read() {
        return medicineServiceRepository.selectAll();
    }

    public void update(MedicineService medicineService) {
        medicineServiceRepository.update(medicineService);
    }

    public void delete(MedicineService medicineService) {
        medicineServiceRepository.delete(medicineService.getId());
    }

    public MedicineService read(Integer id) {
        return medicineServiceRepository.selectById(id);
    }
}
