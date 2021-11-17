package com.truenorth.vhslab.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RENTAL")
public class Rental {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RENTAL_ID")
    private Long rental_id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "USER_ID")
    User user;

    @ManyToOne
    @MapsId("vhs")
    @JoinColumn(name = "VHS_ID")
    VHS vhs;

    @Column(name = "DATE_RENTED")
    private Date date_rented;

    @Column(name = "DATE_RETURNED")
    private Date date_returned;

    public Rental() {
    }

    public Rental(Long rental_id, Date date_rented, Date date_returned) {
        this.rental_id = rental_id;
        this.date_rented = date_rented;
        this.date_returned = date_returned;
    }

    public Long getRental_id() {
        return rental_id;
    }

    public void setRental_id(Long rental_id) {
        this.rental_id = rental_id;
    }

    public Date getDate_rented() {
        return date_rented;
    }

    public void setDate_rented(Date date_rented) {
        this.date_rented = date_rented;
    }

    public Date getDate_returned() {
        return date_returned;
    }

    public void setDate_returned(Date date_returned) {
        this.date_returned = date_returned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VHS getVhs() {
        return vhs;
    }

    public void setVhs(VHS vhs) {
        this.vhs = vhs;
    }

}
