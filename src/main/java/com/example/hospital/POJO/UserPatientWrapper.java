package com.example.hospital.POJO;

public class UserPatientWrapper {
	private User user;
    private Patient patient;

   
    public UserPatientWrapper() {
        this.user = new User(); 
        this.patient = new Patient(); 
    }

   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
