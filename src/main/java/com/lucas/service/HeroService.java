package com.lucas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.entity.Hero;
import com.lucas.repository.HeroRepository;

@Service
public class HeroService {
	
	@Autowired
	private HeroRepository heroRepository;
	
	public List<Hero> findAll() {
		return heroRepository.findAll();
	}
	
	public Optional<Hero> find(Integer id) {
		return heroRepository.findById(id);
	}

	public boolean save(Hero hero) {
		heroRepository.save(hero);
		return true;
	}

	public Hero add(Hero hero) {
		return heroRepository.save(hero);
	}

	public boolean delete(Integer id) {
		heroRepository.deleteById(id);
		return true;
	}
	
}
