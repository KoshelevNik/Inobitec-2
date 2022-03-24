package main.mapper;

import main.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> selectAll();

    void insert(Order order);

    void delete(Integer patientId, Integer serviceId);

    Order selectById(Integer patientId, Integer serviceId);
}
