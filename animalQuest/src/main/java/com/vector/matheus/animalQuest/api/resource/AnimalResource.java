package com.vector.matheus.animalQuest.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vector.matheus.animalQuest.api.dto.AnimalDto;
import com.vector.matheus.animalQuest.api.exception.RegraNegocioException;
import com.vector.matheus.animalQuest.model.entity.Animal;
import com.vector.matheus.animalQuest.model.enums.AmbienteAnimal;
import com.vector.matheus.animalQuest.model.enums.TipoAnimal;
import com.vector.matheus.animalQuest.service.AnimalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/animalquest")
@RequiredArgsConstructor
public class AnimalResource {
	
	private final AnimalService service;

	@PostMapping
	public ResponseEntity salvar( @RequestBody AnimalDto dto ) {
		
		Animal animal = converter(dto);
		
		try {
			Animal animalSalvo = service.salvarAnimalNovo(animal);
			
			return new ResponseEntity(animalSalvo, HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "ambienteAnimal", required  = false) String ambienteAnimal,
			@RequestParam(value = "tipoAnimal", required = true) String tipoAnimal,
			@RequestParam(value = "acao", required = false) String acao
			) {
		Animal animalFiltro = new Animal();
		
		animalFiltro.setAmbienteAnimal(AmbienteAnimal.valueOf(ambienteAnimal));
		animalFiltro.setTipoAnimal(TipoAnimal.valueOf(tipoAnimal));
		animalFiltro.setAcao(acao);
		
		Optional<Animal> animal = service.buscarAnimal(animalFiltro);
		
		return ResponseEntity.ok(animal);
		
	}
	
	@GetMapping("/buscarPorAmbiente")
	public ResponseEntity buscarPorAmbiente(
			@RequestParam(value = "ambienteAnimal", required  = true) String ambienteAnimal) {
		Animal animalFiltro = new Animal();
		
		animalFiltro.setAmbienteAnimal(AmbienteAnimal.valueOf(ambienteAnimal));
		
		Optional<Animal> animal = service.buscarAnimal(animalFiltro);
		
		return ResponseEntity.ok(animal);
		
	}
	
	//Esse é um metodo para conversão não será feito requisição para ele
	private Animal converter(AnimalDto dto) {
		Animal animal = new Animal();
		
		animal.setId(dto.getId());
		animal.setNome(dto.getNome());
		animal.setAcao(dto.getAcao());
		animal.setAmbienteAnimal(AmbienteAnimal.valueOf(dto.getAmbienteAnimal()));
		animal.setTipoAnimal(TipoAnimal.valueOf(dto.getTipoAnimal()));
		
		return animal;
	}
}
