package main.controller;

import main.model.MedicineService;
import main.service.MedicineServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicineService")
public class MedicineServiceController {

    @Autowired
    private MedicineServiceService medicineServiceService;

    @GetMapping("/{id}")
    public MedicineService getMedicineServiceById(@PathVariable Integer id) {
        return medicineServiceService.readMedicineServiceById(id);
    }

    @GetMapping
    public List<MedicineService> getAllMedicineServices() {
        return medicineServiceService.readAllMedicineServices();
    }

    @PostMapping
    public MedicineService createMedicineService(@RequestBody MedicineService medicineService) {
        medicineServiceService.createMedicineService(medicineService);
        return medicineService;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMedicineService(@PathVariable Integer id) {
        return medicineServiceService.deleteMedicineService(id);
    }

    @PutMapping("/{id}")
    public boolean updateMedicineService(@PathVariable Integer id, @RequestBody MedicineService medicineService) {
        return medicineServiceService.updateMedicineService(medicineService, id);
    }
}
