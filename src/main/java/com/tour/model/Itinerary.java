package com.tour.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Itinerary")
public class Itinerary {
	
	@Id
	String id;
	String packageId;
	String day;
	String description;
	
	
	
	public Itinerary() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Itinerary(String id, String packageId, String day, String description) {
		super();
		this.id = id;
		this.packageId = packageId;
		this.day = day;
		this.description = description;
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



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", packageId=" + packageId + ", day=" + day + ", description=" + description
				+ "]";
	}

	


}
