package com.vector.matheus.animalQuest.respository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vector.matheus.animalQuest.model.entity.Animal;
import com.vector.matheus.animalQuest.model.enums.AmbienteAnimal;
import com.vector.matheus.animalQuest.model.enums.TipoAnimal;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AnimalRepositoryTest {	

	@Autowired
	AnimalRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmAnimal() {
		Animal animal = criarAnimal();
		
		entityManager.persist(animal);
		
		boolean resultado = repository.existsByNome("lontra");
		
		Assertions.assertThat(resultado).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverAnimalCadastrado() {
		boolean resultado = repository.existsByNome("lontra");
		
		Assertions.assertThat(resultado).isFalse();
	}
	
	@Test
	public void devePersistirUmAnimalNaBaseDeDados() {
		Animal animal = criarAnimal();
		
		Animal animalSalvo = entityManager.persist(animal);
		
		Assertions.assertThat(animalSalvo.getId()).isNotNull();
	}
	
	public static Animal criarAnimal(){
		return Animal.builder()
				.nome("lontra")
				.acao("l")
				.ambienteAnimal(AmbienteAnimal.AGUA)
				.tipoAnimal(TipoAnimal.MAMIFERO)
				.build();
	}
}
