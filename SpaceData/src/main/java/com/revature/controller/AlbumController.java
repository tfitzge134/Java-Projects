package com.revature.controller;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.AlbumService;

@RestController
@RequestMapping(value = "/album")
public class AlbumController {

	private AlbumService uServ;

	@Autowired
	public AlbumController(AlbumService serv) {
		this.uServ = serv;
	}

	@PostMapping(value = "/create")
	public ResponseEntity<String> createAlbum(@RequestBody LinkedHashMap<String, String> map) {
		String userIdStr = map.get("userId");
		long userId;
		if ((userIdStr == null) || userIdStr.trim().equals("")) {
			userId = -1;
		} else {
			userId = Long.parseLong(userIdStr);
		}
		String message = uServ.createAlbum(map.get("albumName"), userId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
