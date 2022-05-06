package main.service;

import main.model.Order;
import main.model.OrderItem;
import main.model.Patient;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private final String PATIENT_URL = "http://localhost:8081/patient";

    public void createOrder(Order order) {
        orderRepository.insertOrder(order);
    }

    public List<Order> readAllOrders() {
        RestTemplate restTemplate = new RestTemplate();
        Map<Integer, Patient> patients = restTemplate.getForObject(PATIENT_URL, Map.class);
        List<Order> orders = orderRepository.selectAllOrders();
        for (Order order : orders) {
            order.setPatient(patients.get(order.getPatientId()));
        }
        return orders;
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteOrder(id);
    }

    public void updateOrder(Order order, Integer id) {
        order.setId(id);
        for (OrderItem item : order.getOrderItems()) {
            item.setOrderId(id);
        }
        orderRepository.updateOrder(order);
    }

    public Order readOrderById(Integer id) {
        Order order = orderRepository.selectOrderById(id);
        RestTemplate restTemplate = new RestTemplate();
        Patient patient = restTemplate.getForObject(PATIENT_URL + "/" + order.getPatientId(), Patient.class);
        order.setPatient(patient);
        return order;
    }
}
