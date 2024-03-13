package com.tour.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trip")
public class Trip {

    @Id
    String id;
    String packageId;
    String startDate;
    String endDate;
    int totalSeats;
    int minSeats;
    int avlSeats;

    String postponedStatus;


    public Trip() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Trip(String id, String packageId, String startDate, String endDate, int totalSeats, int minSeats, int avlSeats, String postponedStatus) {
        this.id = id;
        this.packageId = packageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSeats = totalSeats;
        this.minSeats = minSeats;
        this.avlSeats = avlSeats;
        this.postponedStatus = postponedStatus;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPackageId() {
        return packageId;
    }


    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }


    public String getStartDate() {
        return startDate;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getEndDate() {
        return endDate;
    }


    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public int getTotalSeats() {
        return totalSeats;
    }


    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }


    public int getMinSeats() {
        return minSeats;
    }


    public void setMinSeats(int minSeats) {
        this.minSeats = minSeats;
    }


    public int getAvlSeats() {
        return avlSeats;
    }


    public void setAvlSeats(int avlSeats) {
        this.avlSeats = avlSeats;
    }

    public String getPostponedStatus() {
        return postponedStatus;
    }

    public void setPostponedStatus(String postponedStatus) {
        this.postponedStatus = postponedStatus;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id='" + id + '\'' +
                ", packageId='" + packageId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", totalSeats=" + totalSeats +
                ", minSeats=" + minSeats +
                ", avlSeats=" + avlSeats +
                ", postponedStatus='" + postponedStatus + '\'' +
                '}';
    }


}
