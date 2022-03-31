package main.service;

import main.model.Order;
import main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public boolean deleteOrder(Integer id) {
        boolean idExist = false;
        for (Order o : readAllOrders()) {
            if (Objects.equals(o.getId(), id)) {
                idExist = true;
                break;
            }
        }
        if (!idExist) return false;
        orderRepository.deleteOrder(id);
        return true;
    }

    public boolean updateOrder(Order order, Integer id) {
        if (Objects.equals(order.getId(), id)) {
            boolean idExist = false;
            for (Order o : readAllOrders()) {
                if (Objects.equals(o.getId(), id)) {
                    idExist = true;
                    break;
                }
            }
            if (!idExist) return false;
            orderRepository.updateOrder(order);
            return true;
        } else {
            return false;
        }
    }

    public Order readOrderById(Integer id) {
        return orderRepository.selectOrderById(id);
    }
}
