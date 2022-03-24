package main.service;

import main.model.Order;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void create(Order order) {
        orderRepository.insert(order);
    }

    public List<Order> read() {
        return orderRepository.selectAll();
    }

    public void delete(Order order) {
        orderRepository.delete(order.getPatientId(), order.getServiceId());
    }

    public Order read(Integer patientId, Integer serviceId) {
        return orderRepository.selectById(patientId, serviceId);
    }
}
