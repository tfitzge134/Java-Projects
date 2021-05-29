package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Album;
import com.revature.repository.AlbumRepository;

@Service
public class AlbumService {

	private AlbumRepository repo;

	public AlbumService() {

	}

	@Autowired
	public AlbumService(AlbumRepository repo) {
		this.repo = repo;
	}

	public String createAlbum(String albumName, long userId) {
		try {
			repo.save(new Album(albumName, userId));
			return "Album created successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: Album was not created";
		}
	}

}
