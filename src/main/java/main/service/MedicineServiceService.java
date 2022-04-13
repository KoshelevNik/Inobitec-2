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

    public void createMedicineService(MedicineService medicineService) {
        medicineServiceRepository.insertMedicineService(medicineService);
    }

    public List<MedicineService> readAllMedicineServices() {
        return medicineServiceRepository.selectAllMedicineServices();
    }

    public void updateMedicineService(MedicineService medicineService, Integer id) {
        medicineService.setId(id);
        medicineServiceRepository.updateMedicineService(medicineService);
    }

    public void deleteMedicineService(Integer id) {
        medicineServiceRepository.deleteMedicineService(id);
    }

    public MedicineService readMedicineServiceById(Integer id) {
        return medicineServiceRepository.selectMedicineServiceById(id);
    }
}
