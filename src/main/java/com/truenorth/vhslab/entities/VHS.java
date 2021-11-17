package com.truenorth.vhslab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VHS")
public class VHS {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VHS_ID")
    private Long vhs_id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "YEAR")
    private Long year;

    @OneToMany(mappedBy = "vhs")
    List<Rental> rentalList;

    public VHS() {
    }

    public VHS(Long id, String title, Long year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getVhs_id() {
        return vhs_id;
    }

    public void setVhs_id(Long vhs_id) {
        this.vhs_id = vhs_id;
    }

    public List<Rental> getRentalList() {
        return rentalList;
    }

    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
    }
}
