package com.kukuhsain.simpletour.guest.model.pojo;

/**
 * Created by kukuh on 02/11/16.
 */

public class Reservation {
    private long reservationId;
    private double pricePerPerson;
    private int numberOfPeople;
    private String createdDate;

    public Reservation(String createdDate, int numberOfPeople, double pricePerPerson, long reservationId) {
        this.createdDate = createdDate;
        this.numberOfPeople = numberOfPeople;
        this.pricePerPerson = pricePerPerson;
        this.reservationId = reservationId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
}
