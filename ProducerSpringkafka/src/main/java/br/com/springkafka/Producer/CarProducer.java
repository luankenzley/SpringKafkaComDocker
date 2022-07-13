package br.com.springkafka.Producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.springkafka.Car;
import br.com.springkafka.People;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CarProducer {
	
	private final String topicName;
	private final KafkaTemplate<String, Car> kafkaTemplate;
	
	
	public CarProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, Car> kafkaTemplate) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(Car car) {
		kafkaTemplate.send(topicName, (String) car.getId(), car).addCallback(
				success -> log.info("Mensagem Enviada com sucesso! "),
				failure -> log.error("Falha ao enviar Mensagem! ")
				);
		
	}
	
	
}
