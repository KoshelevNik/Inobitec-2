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

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.readOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrder() {
        return orderService.readAllOrders();
    }

    @PostMapping
    public Order createNewOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Integer id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
    public boolean updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderService.updateOrder(order, id);
    }
}
