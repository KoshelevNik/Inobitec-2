package main.controller;

import main.model.Order;
import main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/byId")
    public Order orderGETById(@RequestParam Integer patientId, @RequestParam Integer serviceId) {
        return orderService.read(patientId, serviceId);
    }

    @GetMapping("/all")
    public List<Order> orderGETAll() {
        return orderService.read();
    }

    @PostMapping("/create")
    public Order orderPOST(@RequestBody Order order) {
        orderService.create(order);
        return order;
    }

    @DeleteMapping("/delete")
    public Order orderDELETE(@RequestBody Order order) {
        orderService.delete(order);
        return order;
    }
}
