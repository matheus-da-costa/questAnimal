package com.vector.matheus.animalQuest.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vector.matheus.animalQuest.model.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

	boolean existsByNome(String nome);

}
