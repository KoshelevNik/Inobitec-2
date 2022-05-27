package main.service;

import main.configuration.DataBaseFrameworkConfiguration;
import main.mapper.MedicineServiceMapper;
import main.model.MedicineService;
import main.repository.MedicineServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceService {

    @Autowired
    private MedicineServiceRepository medicineServiceRepository;

    @Autowired
    private MedicineServiceMapper medicineServiceMapper;

    @Autowired
    private DataBaseFrameworkConfiguration dataBaseFrameworkConfiguration;

    public void createMedicineService(MedicineService medicineService) {
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            medicineServiceMapper.insertMedicineService(medicineService);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            medicineServiceRepository.insert(
                    medicineService.getName(),
                    medicineService.getCost(),
                    medicineService.getDescription(),
                    medicineService.getDoctorSpecialistName()
            );
        }
    }

    public List<MedicineService> readAllMedicineServices() {
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            return medicineServiceMapper.selectAllMedicineServices();
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            return medicineServiceRepository.findAll();
        } else {
            return null;
        }
    }

    public void updateMedicineService(MedicineService medicineService, Integer id) {
        medicineService.setId(id);
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            medicineServiceMapper.updateMedicineService(medicineService);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            medicineServiceRepository.save(medicineService);
        }
    }

    public void deleteMedicineService(Integer id) {
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            medicineServiceMapper.deleteMedicineService(id);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            medicineServiceRepository.deleteById(id);
        }
    }

    public MedicineService readMedicineServiceById(Integer id) {
        if (dataBaseFrameworkConfiguration.getFramework().equals("mybatis")) {
            return medicineServiceMapper.selectMedicineServiceById(id);
        } else if (dataBaseFrameworkConfiguration.getFramework().equals("hibernate")) {
            return medicineServiceRepository.findById(id).orElse(null);
        } else {
            return null;
        }
    }
}
