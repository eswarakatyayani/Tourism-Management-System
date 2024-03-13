package com.tour.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Booking")
public class Booking {

    @Id
    String id;
    String bookingId;
    String packageId;
    String tripId;
    String custId;
    String bookedDate;
    int adult;
    int child;
    List<Tourist> tourists;
    double amount;
    String bookingStatus;

    Payment payment;
    boolean isTripPosponded;

    public Booking() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Booking(String id, String bookingId, String packageId, String tripId, String custId, String bookedDate,
            int adult, int child, List<Tourist> tourists, double amount, String bookingStatus, Payment payment,
            boolean isTripPosponded) {
        this.id = id;
        this.bookingId = bookingId;
        this.packageId = packageId;
        this.tripId = tripId;
        this.custId = custId;
        this.bookedDate = bookedDate;
        this.adult = adult;
        this.child = child;
        this.tourists = tourists;
        this.amount = amount;
        this.bookingStatus = bookingStatus;
        this.payment = payment;
        this.isTripPosponded = isTripPosponded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public void setTourists(List<Tourist> tourists) {
        this.tourists = tourists;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isTripPosponded() {
        return isTripPosponded;
    }

    public void setTripPosponded(boolean tripPosponded) {
        isTripPosponded = tripPosponded;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", packageId='" + packageId + '\'' +
                ", tripId='" + tripId + '\'' +
                ", custId='" + custId + '\'' +
                ", bookedDate='" + bookedDate + '\'' +
                ", adult=" + adult +
                ", child=" + child +
                ", tourists=" + tourists +
                ", amount=" + amount +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", payment=" + payment +
                ", isTripPosponded=" + isTripPosponded +
                '}';
    }

}
