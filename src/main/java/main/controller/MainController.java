package main.controller;

import main.model.MedicineService;
import main.model.Order;
import main.model.Patient;
import main.service.MedicineServiceService;
import main.service.OrderService;
import main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineServiceService medicineServiceService;

    @GetMapping("/")
    public ModelAndView indexGET() {
        return new ModelAndView("index");
    }

    @PostMapping("/")
    public ModelAndView indexPOST() {
        return new ModelAndView("index");
    }

    @DeleteMapping("/")
    public ModelAndView indexDELETE() {
        return new ModelAndView("index");
    }

    @PutMapping("/")
    public ModelAndView indexPUT() {
        return new ModelAndView("index");
    }
}
