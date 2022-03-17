package main.model;

public class MedicineService {

    private Integer cost;
    private String name, description, doctor_specialist_name, id;

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorSpecialistName() {
        return doctor_specialist_name;
    }

    public void setDoctorSpecialistName(String doctorSpecialistName) {
        this.doctor_specialist_name = doctorSpecialistName;
    }
}
