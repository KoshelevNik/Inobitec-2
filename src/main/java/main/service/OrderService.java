package main.service;

import main.mapper.OrderMapper;
import main.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void create(Order order) {
        orderMapper.insert(UUID.fromString(order.getPatientUUID()), UUID.fromString(order.getMedicineServiceUUID()), order.getDate());
    }

    public List<Order> read() {
        return orderMapper.selectAll();
    }

    public void delete(Order order) {
        orderMapper.delete(UUID.fromString(order.getPatientUUID()), UUID.fromString(order.getMedicineServiceUUID()));
    }

    public Order read(UUID patientId, UUID serviceId) {
        return orderMapper.selectById(patientId, serviceId);
    }
}
