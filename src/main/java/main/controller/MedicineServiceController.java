package main.controller;

import main.model.MedicineService;
import main.service.MedicineServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class MedicineServiceController {

    @Autowired
    private MedicineServiceService medicineServiceService;

    @GetMapping("/byId")
    public MedicineService medicineServiceGETById(@RequestParam Integer id) {
        return medicineServiceService.read(id);
    }

    @GetMapping("/all")
    public List<MedicineService> medicineServiceGETAll() {
        return medicineServiceService.read();
    }

    @PostMapping("/create")
    public MedicineService medicineServicePOST(@RequestBody MedicineService medicineService) {
        medicineServiceService.create(medicineService);
        return medicineService;
    }

    @DeleteMapping("/delete")
    public MedicineService medicineServiceDELETE(@RequestBody MedicineService medicineService) {
        medicineServiceService.delete(medicineService);
        return medicineService;
    }

    @PutMapping("/update")
    public MedicineService medicineServicePUT(@RequestBody MedicineService medicineService) {
        medicineServiceService.update(medicineService);
        return medicineService;
    }
}
