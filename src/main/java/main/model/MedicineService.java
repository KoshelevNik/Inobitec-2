package main.model;

public class MedicineService {

    private Integer cost, id;
    private String name, description, doctorSpecialistName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return doctorSpecialistName;
    }

    public void setDoctorSpecialistName(String doctorSpecialistName) {
        this.doctorSpecialistName = doctorSpecialistName;
    }
}
