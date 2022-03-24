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

    public void insert(MedicineService medicineService) {
        medicineServiceMapper.insert(medicineService);
    }

    public void delete(Integer id) {
        medicineServiceMapper.delete(id);
    }

    public void update(MedicineService medicineService) {
        medicineServiceMapper.update(medicineService);
    }

    public List<MedicineService> selectAll() {
        return medicineServiceMapper.selectAll();
    }

    public MedicineService selectById(Integer id) {
        return medicineServiceMapper.selectById(id);
    }
}
