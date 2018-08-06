package com.openshift.model;

public class Review {

	
	private String username;
	private int rating;
	private boolean approved;
	
	public Review() {
		super();
		
	}
	
	public Review(String username, int rating, boolean approved) {
		super();
		this.username = username;
		this.rating = rating;
		this.approved = approved;
	}


	public String getUsername() {
		return username;
	}


	public int getRating() {
		return rating;
	}


	public boolean isApproved() {
		return approved;
	}
	
	
	
}
