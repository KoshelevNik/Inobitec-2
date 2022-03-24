package main.mapper;

import main.model.MedicineService;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicineServiceMapper {

    List<MedicineService> selectAll();

    void insert(MedicineService medicineService);

    void delete(Integer id);

    void update(MedicineService medicineService);

    MedicineService selectById(Integer id);
}
