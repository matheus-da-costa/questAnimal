package com.vector.matheus.animalQuest.service;

import java.util.Optional;

import com.vector.matheus.animalQuest.model.entity.Animal;

public interface AnimalService {
	
	Optional<Animal> buscarAnimal(Animal animmalFiltro);	
	
	void validarAnimal(String nome);
	
	Animal salvarAnimalNovo(Animal animal);

}
