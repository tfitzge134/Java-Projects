package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "album")
public class Album {

	@Id
	@Column(name = "album_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;

	@Column(name = "album_name", unique = true, nullable = false)
	private String albumName;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	public Album() {
		super();
	}

	public Album(String albumName, Long userId) {
		super();
		this.albumName = albumName;
		this.userId = userId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName + ", userId=" + userId + "]";
	}

}
