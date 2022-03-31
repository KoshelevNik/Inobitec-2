package main.repository;

import main.mapper.MedicineServiceMapper;
import main.model.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicineServiceRepository {

    @Autowired
    private MedicineServiceMapper medicineServiceMapper;

    public void insertMedicineService(MedicineService medicineService) {
        medicineServiceMapper.insertMedicineService(medicineService);
    }

    public void deleteMedicineService(Integer id) {
        medicineServiceMapper.deleteMedicineService(id);
    }

    public void updateMedicineService(MedicineService medicineService) {
        medicineServiceMapper.updateMedicineService(medicineService);
    }

    public List<MedicineService> selectAllMedicineServices() {
        return medicineServiceMapper.selectAllMedicineServices();
    }

    public MedicineService selectMedicineServiceById(Integer id) {
        return medicineServiceMapper.selectMedicineServiceById(id);
    }
}
