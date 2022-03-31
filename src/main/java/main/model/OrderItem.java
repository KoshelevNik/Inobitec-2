package main.model;

public class OrderItem {

    private Integer medicineServiceId, orderId;

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
