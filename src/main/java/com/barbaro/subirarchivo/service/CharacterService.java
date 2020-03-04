package com.barbaro.subirarchivo.service;

import java.util.List;
import com.barbaro.subirarchivo.model.Character;

public interface CharacterService {
	
	List<Character> getAll();
	Character save(Character character);
	Character getById(Integer idCharacter);
	Character update(Integer idCharacter, Character character);
	void delete(Integer idCharacter);
}
