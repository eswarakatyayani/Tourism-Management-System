package com.tour.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TourPack")
public class Pack {

    @Id
    String id;
    String packageId;
    String packageName;
    String packageType;
    String locations;
    String duration;
    String inclusions;
    String exclusions;
    String imgUrl;
    String adultPrice;
    String childPrice;


    public Pack() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Pack(String id, String packageId, String packageName, String packageType, String locations, String duration,
                String inclusions, String exclusions, String imgUrl, String adultPrice, String childPrice) {
        super();
        this.id = id;
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageType = packageType;
        this.locations = locations;
        this.duration = duration;
        this.inclusions = inclusions;
        this.exclusions = exclusions;
        this.imgUrl = imgUrl;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
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


    public String getPackageName() {
        return packageName;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public String getPackageType() {
        return packageType;
    }


    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }


    public String getLocations() {
        return locations;
    }


    public void setLocations(String locations) {
        this.locations = locations;
    }


    public String getDuration() {
        return duration;
    }


    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getInclusions() {
        return inclusions;
    }


    public void setInclusions(String inclusions) {
        this.inclusions = inclusions;
    }


    public String getExclusions() {
        return exclusions;
    }


    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public String getAdultPrice() {
        return adultPrice;
    }


    public void setAdultPrice(String adultPrice) {
        this.adultPrice = adultPrice;
    }


    public String getChildPrice() {
        return childPrice;
    }


    public void setChildPrice(String childPrice) {
        this.childPrice = childPrice;
    }


    @Override
    public String toString() {
        return "Pack [id=" + id + ", packageId=" + packageId + ", packageName=" + packageName + ", packageType="
                + packageType + ", locations=" + locations + ", duration=" + duration + ", inclusions=" + inclusions
                + ", exclusions=" + exclusions + ", imgUrl=" + imgUrl + ", adultPrice=" + adultPrice + ", childPrice="
                + childPrice + "]";
    }


}
