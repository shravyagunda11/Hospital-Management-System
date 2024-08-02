package com.example.hospital.POJO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appointment")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int apptId;
	@Column
	private LocalDate apptDate;
	@Column
	private LocalTime apptTime;
	@Column
	private String Status;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
	private Patient pat;
	   
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
	private Doctor doc;
	@Column
	String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDate getApptDate() {
		return apptDate;
	}

	public void setApptDate(LocalDate apptDate) {
		this.apptDate = apptDate;
	}

	public LocalTime getApptTime() {
		return apptTime;
	}

	public void setApptTime(LocalTime apptTime) {
		this.apptTime = apptTime;
	}

	public Patient getPat() {
		return pat;
	}

	public void setPat(Patient pat) {
		this.pat = pat;
	}

	public Doctor getDoc() {
		return doc;
	}

	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	
	public int getApptId() {
		return apptId;
	}

	public void setApptId(int apptId) {
		this.apptId = apptId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Appointment() {
		
	}
}
