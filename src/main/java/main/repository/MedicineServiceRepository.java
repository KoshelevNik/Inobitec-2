package main.repository;

import main.model.MedicineService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MedicineServiceRepository extends JpaRepository<MedicineService, Integer> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO \"medicineService\" VALUES (?, ?, nextval('medicineServiceIdSequence'), ?, ?)", nativeQuery = true)
    void insert(String name, Integer cost, String description, String doctorSpecialistName);
}
