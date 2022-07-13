package br.com.springkafka.controller.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarDTO {
	
	private String id;
	private String name;
	private String brand;

}
