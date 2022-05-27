package main.repository;

import main.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO \"orderItem\" VALUES (?, ?)", nativeQuery = true)
    void insert(Integer medicineServiceId, Integer orderId);

    @Query(value = "SELECT * FROM \"orderItem\" WHERE \"orderId\"=?", nativeQuery = true)
    List<OrderItem> selectOrderItemsById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM \"orderItem\" WHERE \"orderId\"=?", nativeQuery = true)
    void deleteAllById(Integer id);
}
