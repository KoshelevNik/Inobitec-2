package main.service;

import main.model.MedicineService;
import main.repository.MedicineServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public boolean updateMedicineService(MedicineService medicineService, Integer id) {
        if (Objects.equals(medicineService.getId(), id)) {
            boolean idExist = false;
            for (MedicineService ms : readAllMedicineServices()) {
                if (Objects.equals(ms.getId(), id)) {
                    idExist = true;
                    break;
                }
            }
            if (!idExist) return false;
            medicineServiceRepository.updateMedicineService(medicineService);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMedicineService(Integer id) {
        boolean idExist = false;
        for (MedicineService ms : readAllMedicineServices()) {
            if (Objects.equals(ms.getId(), id)) {
                idExist = true;
                break;
            }
        }
        if (!idExist) return false;
        medicineServiceRepository.deleteMedicineService(id);
        return true;
    }

    public MedicineService readMedicineServiceById(Integer id) {
        return medicineServiceRepository.selectMedicineServiceById(id);
    }
}
