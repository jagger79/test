package cz.rkr.movies.be.war.rest;

import java.util.Date;

import org.springframework.validation.annotation.Validated;

@Validated
public class TestDomain {

	private String text;
	private Date now;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

}
