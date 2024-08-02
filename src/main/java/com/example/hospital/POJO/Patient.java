package com.example.hospital.POJO;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "Patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int patientId;
	

	@Column(name = "First_Name")
	private String fname;
	@Column(name = "Last_Name")
	private String lname;
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", fname=" + fname + ", lname=" + lname + ", dob=" + dob
				+ ", gender=" + gender + "]";
	}

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private LocalDate dob;
	@Column(name ="Gender")
	private String gender;
	
	//joining table user and patient
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
	private User user;
	   
	

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Patient() {
        
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int studentId) {
        this.patientId = studentId;
    }
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
	
}
