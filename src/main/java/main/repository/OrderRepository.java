package main.repository;

import main.mapper.OrderMapper;
import main.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    public void insert(Order order) {
        orderMapper.insert(order);
    }

    public void delete(Integer patientId, Integer serviceId) {
        orderMapper.delete(patientId, serviceId);
    }

    public Order selectById(Integer patientId, Integer serviceId) {
        return orderMapper.selectById(patientId, serviceId);
    }
}
