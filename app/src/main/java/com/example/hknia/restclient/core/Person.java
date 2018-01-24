package com.example.hknia.restclient.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Person {
    @JsonProperty("ID")
    private long ID;

    @JsonProperty("FirstName")
    private String FirstName;

    @JsonProperty("LastName")
    private String LastName;

    @JsonProperty("PayRate")
    private double PayRate;

    @JsonProperty("StartDate")
    private Date StartDate;

    @JsonProperty("EndDate")
    private Date EndDate;

    public long getID() {
        return this.ID;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public double getPayRate() {
        return PayRate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setID(long id) {
        ID = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }


    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPayRate(double payRate) {
        PayRate = payRate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }
}
