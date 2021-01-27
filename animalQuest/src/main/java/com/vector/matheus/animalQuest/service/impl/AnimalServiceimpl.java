package com.vector.matheus.animalQuest.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vector.matheus.animalQuest.api.exception.RegraNegocioException;
import com.vector.matheus.animalQuest.model.entity.Animal;
import com.vector.matheus.animalQuest.respository.AnimalRepository;
import com.vector.matheus.animalQuest.service.AnimalService;

@Service
public class AnimalServiceimpl implements AnimalService{
	
	private AnimalRepository repository;

	public AnimalServiceimpl(AnimalRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	public void validarAnimal(String nome) {
		boolean existe = repository.existsByNome(nome);
		if(existe) {
			throw new RegraNegocioException("Já existe este animal cadastrado!");
		}
	}	


	@Override
	@Transactional
	public Animal salvarAnimalNovo(Animal animal) {
		validarAnimal(animal.getNome());	
		return repository.save(animal);
	}

	@Override
	public Optional<Animal> buscarAnimal(Animal animmalFiltro) {
		Example example = Example.of( animmalFiltro, 
						ExampleMatcher.matching()
							.withIgnoreCase()
							.withStringMatcher(StringMatcher.CONTAINING ));
		
		return repository.findOne(example);
	}

	
}
