package com.example.hospital.POJO;

public class UserDoctorWrapper {

	private User user;
    private Doctor doctor;

   
    public UserDoctorWrapper() {
        this.user = new User(); 
        this.doctor = new Doctor(); 
    }

   
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
