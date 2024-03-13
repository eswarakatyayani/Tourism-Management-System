package com.tour.model;

public class Tourist {
	String touristName;
	String touristType;
	
	public Tourist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tourist(String touristName, String touristType) {
		super();
		this.touristName = touristName;
		this.touristType = touristType;
	}

	public String getTouristName() {
		return touristName;
	}

	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}

	public String getTouristType() {
		return touristType;
	}

	public void setTouristType(String touristType) {
		this.touristType = touristType;
	}

	@Override
	public String toString() {
		return "Tourist [touristName=" + touristName + ", touristType=" + touristType + "]";
	}

	

}
