package main.model;

import java.util.Date;

public class Order {

    private String patient_id, service_id;
    private Date date;

    public String getPatientUUID() {
        return patient_id;
    }

    public void setPatientUUID(String patientUUID) {
        this.patient_id = patientUUID;
    }

    public String getMedicineServiceUUID() {
        return service_id;
    }

    public void setMedicineServiceUUID(String medicineServiceUUID) {
        this.service_id = medicineServiceUUID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
