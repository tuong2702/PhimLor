package com.phimlor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Shares")
public class Share {
	@Id
	@Column(name="ShareId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shareId;

	@Column(name="Email")
	private String email;

	@Column(name="SharedDate")
	private Date sharedDate;
	
	@ManyToOne
	@JoinColumn(name="UserId")
	private User user;

	@ManyToOne
	@JoinColumn(name="VideoId")
	private Video video;

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
}
