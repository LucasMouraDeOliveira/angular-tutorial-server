package com.lucas.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.PathParam;

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
import org.springframework.web.bind.annotation.RestController;

import com.lucas.entity.Hero;
import com.lucas.service.HeroService;

@RestController
@RequestMapping(path = "hero")
@CrossOrigin
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	@GetMapping
	public ResponseEntity<List<Hero>> listHeroes() {
		List<Hero> heroes = heroService.findAll();
		return ResponseEntity.ok(heroes);
	}
	
	@GetMapping(path = "{id}")
	public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
		Optional<Hero> hero = heroService.find(id);
		if(hero.isPresent()) {
			return ResponseEntity.ok(hero.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateHero(@RequestBody Hero hero) {
		if(heroService.save(hero)) {
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping
	public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
		Hero newHero = heroService.add(hero);
		if(newHero != null) {
			return ResponseEntity.ok(newHero);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(path = "{id}")
	public ResponseEntity<Void> deleteHero(@PathVariable Integer id) {
		if(heroService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Hero>> findHeroes(@PathParam("name") String name) {
		List<Hero> heroes = this.heroService.findAll().stream()
				.filter(h -> h.getName().contains(name))
				.collect(Collectors.toList());
		return ResponseEntity.ok(heroes);
	}

}
