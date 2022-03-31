package main.mapper;

import main.model.MedicineService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicineServiceMapper {

    List<MedicineService> selectAllMedicineServices();

    void insertMedicineService(MedicineService medicineService);

    void deleteMedicineService(Integer id);

    void updateMedicineService(MedicineService medicineService);

    MedicineService selectMedicineServiceById(Integer id);
}
