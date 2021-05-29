package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

	Album findAlbumByAlbumName(String albumName);

}