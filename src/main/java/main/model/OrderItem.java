package main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"orderItem\"")
public class OrderItem {

    @Column(name = "\"medicineServiceId\"")
    private Integer medicineServiceId;
    @Id
    @Column(name = "\"orderId\"")
    private Integer orderId;

    public Integer getMedicineServiceId() {
        return medicineServiceId;
    }

    public void setMedicineServiceId(Integer medicineServiceId) {
        this.medicineServiceId = medicineServiceId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
