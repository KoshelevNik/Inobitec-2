package main.controller;

import main.exception.OrderNotFoundException;
import main.exception.PatientNotFoundException;
import main.model.Order;
import main.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String nullPointerExceptionHandler(OrderNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String patientNotFoundExceptionHandler(PatientNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler({ResourceAccessException.class, HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String resourceAccessExceptionHandler() {
        return "Patients data not available";
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) throws OrderNotFoundException, PatientNotFoundException {
        return orderService.readOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrder() {
        return orderService.readAllOrders();
    }

    @PostMapping
    public Order createNewOrder(@RequestBody Order order) throws PatientNotFoundException {
        orderService.createOrder(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody Order order) throws PatientNotFoundException {
        orderService.updateOrder(order, id);
    }
}
