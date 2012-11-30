package com.demo.domain;

import java.util.Date;

public class IdCard {
	private int id;
	private Date usefulLife;

	public void setUsefulLife(Date usefulLife) {
		this.usefulLife = usefulLife;
	}

	public Date getUsefulLife() {
		return usefulLife;
	}

	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
