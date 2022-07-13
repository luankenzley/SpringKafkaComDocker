package br.com.springkafka.controller.dtos;

import java.util.List;

import lombok.Getter;

@Getter
public class PeopleDTO {
	
	private String name;
	private String cpf;
	
	private List<String> books;

}
