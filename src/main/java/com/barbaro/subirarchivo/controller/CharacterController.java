package com.barbaro.subirarchivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.barbaro.subirarchivo.service.CharacterService;
import com.barbaro.subirarchivo.service.CloudStorageService;
import com.barbaro.subirarchivo.model.Character;

@RestController
@CrossOrigin
@RequestMapping("/characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	@Autowired
	private CloudStorageService storageService;
	
	@GetMapping
	public List<Character> getCharactersList(){
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
		return new ResponseEntity<Character>(service.save(character), HttpStatus.CREATED);
	}
	
	@PostMapping("/uploadfile")
	@CrossOrigin
	public ResponseEntity<String> uploadSingleFile(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		storageService.uploadFile(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/uploadfile1")
	@CrossOrigin
	public ResponseEntity<String> uploadSingleFile(){
		return new ResponseEntity<>("Yey", HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Character> getCharacterById(@PathVariable("id") Integer idCharacter){
		return new ResponseEntity<Character>(service.getById(idCharacter), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Character> updateCharacterById(@PathVariable("id") Integer idCharacter, @RequestBody Character character){
		return new ResponseEntity<Character>(service.update(idCharacter, character), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCharacterById(@PathVariable("id") Integer idCharacter){
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
