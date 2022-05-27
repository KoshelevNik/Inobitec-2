package main.repository;

import main.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO \"order\" VALUES (?, ?, nextval('orderIdSequence'))", nativeQuery = true)
    void insert(Integer patientId, Date date);
}
