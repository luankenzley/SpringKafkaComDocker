package br.com.springkafka.controller;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springkafka.People;
import br.com.springkafka.Producer.PeopleProducer;
import br.com.springkafka.controller.dtos.PeopleDTO;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/peoples")
@AllArgsConstructor
public class PeopleController {
	
	private final PeopleProducer peopleProducer;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO) {
		var id = UUID.randomUUID().toString();
		
		var message = People.newBuilder()
				.setId(id)
				.setName(peopleDTO.getName())
				.setCpf(peopleDTO.getCpf())
				.setBooks(peopleDTO.getBooks().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
				.build();
		
		peopleProducer.sendMessage(message);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
}
