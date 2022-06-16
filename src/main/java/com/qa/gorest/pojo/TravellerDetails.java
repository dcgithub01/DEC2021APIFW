package com.qa.gorest.pojo;

public class TravellerDetails {
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private String additionalneeds;
	private BookingDates Bookingdates;
	public TravellerDetails(String firstname, String lastname, int totalprice, boolean depositpaid,
			String additionalneeds, com.qa.gorest.pojo.BookingDates Bookingdates) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.additionalneeds = additionalneeds;
		this.Bookingdates = Bookingdates;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public boolean isDepositpaid() {
		return depositpaid;
	}
	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	public String getAdditionalneeds() {
		return additionalneeds;
	}
	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}
	public BookingDates getBookingdates() {
		return Bookingdates;
	}
	public void setBookingdates(BookingDates bookingdates) {
		Bookingdates = bookingdates;
	}
	
	
	

}
	