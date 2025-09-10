package com.phoneBook.model;

import java.sql.Date;
import java.time.LocalDate;

public class Contact {
private int id;
private String name;
private long phone;
private String email;
private Date dob;
public Contact(int id, String name, long phone, String email, Date dob) {
	super();
	this.id = id;
	this.name = name;
	this.phone = phone;
	this.email = email;
	this.dob = dob;
}
public Contact() {

}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
@Override
public String toString() {
	return "Contact [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", dob=" + dob + "]";
}


}
