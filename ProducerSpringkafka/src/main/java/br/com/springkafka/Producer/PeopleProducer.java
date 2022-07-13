package br.com.springkafka.Producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.springkafka.People;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PeopleProducer {
	
	private final String topicName;
	private final KafkaTemplate<String, People> kafkaTemplate;
	
	
	public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage(People people) {
		kafkaTemplate.send(topicName, people).addCallback(
				success -> log.info("Mensagem Enviada com sucesso! "),
				failure -> log.error("Falha ao enviar Mensagem! ")
				);
		
	}
	
	
}
