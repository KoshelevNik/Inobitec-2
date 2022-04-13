package main.service;

import main.model.Order;
import main.model.OrderItem;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Order order) {
        orderRepository.insertOrder(order);
    }

    public List<Order> readAllOrders() {
        return orderRepository.selectAllOrders();
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
        return orderRepository.selectOrderById(id);
    }
}
