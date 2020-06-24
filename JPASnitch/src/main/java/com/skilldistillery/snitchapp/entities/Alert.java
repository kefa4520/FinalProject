package com.skilldistillery.snitchapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Alert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String subject;
	
	private String content;
	
	@CreationTimestamp
	@Column (name= "created_at")
	private LocalDateTime createdAt; 
	
	private Boolean expired;

	@ManyToOne
	@JoinColumn (name= "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn (name= "category_id")
	private Category cagtegory;
	
	// method
	
	public Alert() {
		super();
	}

	public Alert(int id, Category cagtegory, String subject, String content, LocalDateTime createdAt, Boolean expired,
			User user) {
		super();
		this.id = id;
		this.cagtegory = cagtegory;
		this.subject = subject;
		this.content = content;
		this.createdAt = createdAt;
		this.expired = expired;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCagtegory() {
		return cagtegory;
	}

	public void setCagtegory(Category cagtegory) {
		this.cagtegory = cagtegory;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alert other = (Alert) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", cagtegory=" + cagtegory + ", subject=" + subject + ", content=" + content
				+ ", createdAt=" + createdAt + ", expired=" + expired + ", user=" + user + "]";
	}

	
	
}