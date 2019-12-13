package com.lucas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.entity.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
