package com.barbaro.subirarchivo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.barbaro.subirarchivo.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
