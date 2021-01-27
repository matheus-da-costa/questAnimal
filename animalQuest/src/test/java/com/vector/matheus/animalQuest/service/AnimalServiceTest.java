package com.vector.matheus.animalQuest.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vector.matheus.animalQuest.api.exception.RegraNegocioException;
import com.vector.matheus.animalQuest.model.entity.Animal;
import com.vector.matheus.animalQuest.model.enums.AmbienteAnimal;
import com.vector.matheus.animalQuest.model.enums.TipoAnimal;
import com.vector.matheus.animalQuest.respository.AnimalRepository;
import com.vector.matheus.animalQuest.respository.AnimalRepositoryTest;
import com.vector.matheus.animalQuest.service.impl.AnimalServiceimpl;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AnimalServiceTest {
	
	@SpyBean
	AnimalServiceimpl service;
	
	@MockBean
	AnimalRepository repository;
	
	@Test(expected = Test.None.class)
	public void deveSalvarAnimal() {
		Mockito.doNothing().when(service).validarAnimal(Mockito.anyString());
		
		Animal animal = AnimalRepositoryTest.criarAnimal();
		
		 animal.setId(3l);
		 
		 Mockito.when(repository.save(Mockito.any(Animal.class))).thenReturn(animal);
		 
		 Animal animalSalvo = service.salvarAnimalNovo(new Animal());
		 
		 Assertions.assertThat(animalSalvo).isNotNull();
		 Assertions.assertThat(animalSalvo.getId()).isEqualTo(3l);
		 Assertions.assertThat(animal.getNome()).isEqualTo("lontra");
		 Assertions.assertThat(animalSalvo.getAcao()).isEqualTo("l");
		 Assertions.assertThat(animalSalvo.getAmbienteAnimal()).isEqualTo(AmbienteAnimal.AGUA);
		 Assertions.assertThat(animalSalvo.getTipoAnimal()).isEqualTo(TipoAnimal.MAMIFERO);
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void NaoDeveSalvarUmAnimalComNomeJaCadastrado() {
		Animal animal = AnimalRepositoryTest.criarAnimal();
		
		String nomeAnimal = animal.getNome();
		
		Mockito.doThrow(RegraNegocioException.class).when(service).validarAnimal(nomeAnimal);
		
		service.salvarAnimalNovo(animal);
		
		Mockito.verify(repository, Mockito.never()).save(animal);
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarAnimal() {
		Mockito.when(repository.existsByNome(Mockito.anyString())).thenReturn(false);
		
		Animal animal = AnimalRepositoryTest.criarAnimal();

		
		service.validarAnimal("lontra");
		
	}
	
	@Test(expected = RegraNegocioException.class)
	public void deveLancarErroAoTentarValidarAnimal() {
		Mockito.when(repository.existsByNome(Mockito.anyString())).thenReturn(true);
		
		service.validarAnimal("lontra");;
	}
	
	public void deveBuscarAnimal() {
		Animal animal = AnimalRepositoryTest.criarAnimal();
		
		animal.setId(1l);
		
		Mockito.when( repository.findOne(Mockito.any(Example.class)) ).thenReturn(animal);
		
		Optional<Animal> resultado = service.buscarAnimal(animal);
		
		Assertions.assertThat(animal).isNotNull();
		Assertions.assertThat(animal.getId()).isEqualTo(1l);
		Assertions.assertThat(animal.getNome()).isEqualTo("lontra");
		Assertions.assertThat(animal.getAcao()).isEqualTo("l");
		Assertions.assertThat(animal.getAmbienteAnimal()).isEqualTo(AmbienteAnimal.AGUA);
		Assertions.assertThat(animal.getTipoAnimal()).isEqualTo(TipoAnimal.MAMIFERO);
		
	}
	
	//Esse é uma metodo que criar um builder de um animal
	public static Animal criarAnimal(){
		return Animal.builder()
				.nome("lontra")
				.acao("l")
				.ambienteAnimal(AmbienteAnimal.AGUA)
				.tipoAnimal(TipoAnimal.MAMIFERO)
				.build();
	}

}
