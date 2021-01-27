package com.vector.matheus.animalQuest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
	
	private Long id;
	private String nome;
	private String acao;
	private String ambienteAnimal;
	private String tipoAnimal;

}
