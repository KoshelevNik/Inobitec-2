package main.mapper;

import main.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> selectAllOrders();

    void insertOrder(Order order);

    void deleteOrder(Integer id);

    void updateOrder(Order order);

    Order selectOrderById(Integer id);
}
