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

    public List<Order> selectAllOrders() {
        return orderMapper.selectAllOrders();
    }

    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

    public void deleteOrder(Integer id) {
        orderMapper.deleteOrder(id);
    }

    public void updateOrder(Order order) {
        orderMapper.updateOrder(order);
    }

    public Order selectOrderById(Integer id) {
        return orderMapper.selectOrderById(id);
    }
}
