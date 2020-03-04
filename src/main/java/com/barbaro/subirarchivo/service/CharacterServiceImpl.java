package com.barbaro.subirarchivo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbaro.subirarchivo.dao.CharacterRepository;
import com.barbaro.subirarchivo.model.Character;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
	private CharacterRepository repository;

	@Override
	public List<Character> getAll() {
		return repository.findAll();
	}

	@Override
	public Character save(Character character) {
		character.setCreatedAt(new Date());
		return repository.save(character);
	}

	@Override
	public Character getById(Integer idCharacter) {
		return repository.findById(idCharacter).get();
	}

	@Override
	public Character update(Integer idCharacter, Character character) {
		Character characterDB = getById(idCharacter);
		characterDB.setName(character.getName());
		characterDB.setDescription(character.getDescription());
		characterDB.setCartoon(character.getCartoon());
		return repository.save(characterDB);
	}

	@Override
	public void delete(Integer idCharacter) {
		repository.deleteById(idCharacter);
	}
}
