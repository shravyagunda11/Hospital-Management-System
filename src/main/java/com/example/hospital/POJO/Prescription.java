package com.example.hospital.POJO;

import java.util.ArrayList;
import java.util.List;

public class Prescription {
	private List<Tablet> tablets;

    public List<Tablet> getTablets() {
        return tablets;
    }

    public void setTablets(List<Tablet> tabletEntries) {
        this.tablets = tabletEntries;
    }
    public Prescription() {
        this.tablets = new ArrayList<>();
    }

}
