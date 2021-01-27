package com.vector.matheus.animalQuest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vector.matheus.animalQuest.model.enums.AmbienteAnimal;
import com.vector.matheus.animalQuest.model.enums.TipoAnimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "animal", schema = "animais")
public class Animal {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "ambiente")
	@Enumerated(value = EnumType.STRING)
	private AmbienteAnimal ambienteAnimal;
	
	@Column(name = "tipo")
	@Enumerated(value = EnumType.STRING)
	private TipoAnimal tipoAnimal;
	
	@Column(name = "acao")
	private String acao;

}
