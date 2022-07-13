package br.com.springkafka.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springkafka.Car;
import br.com.springkafka.Producer.CarProducer;
import br.com.springkafka.controller.dtos.CarDTO;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
	
	private final CarProducer carProducer;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> sendMessage(@RequestBody CarDTO carDto) {
		
		var id = UUID.randomUUID().toString();
		
		var message = Car.newBuilder()
				.setId(id)
				.setName(carDto.getName())
				.setBrand(carDto.getBrand()).build();
		
		carProducer.sendMessage(message);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
		
	}

}
