package main.exception;

public class PatientNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Patient not found";
    }
}
