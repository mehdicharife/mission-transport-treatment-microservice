package io.github.mehdicharife.transporttreatmentmicroservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.mehdicharife.transporttreatmentmicroservice.event.MissionTransportTreatedEvent;

@SpringBootApplication
public class TransportTreatmentMicroserviceApplication {

	@Value("${missionTransportTreatedTopic}")
	private String MISSION_TRANSPORT_TREATED_EXCHANGE_NAME;

	public static void main(String[] args) {
		SpringApplication.run(TransportTreatmentMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RabbitTemplate rabbitTemplate) {
		return args -> {
			MissionTransportTreatedEvent event = new MissionTransportTreatedEvent((long) 1,(long) 2);
			rabbitTemplate.convertAndSend(MISSION_TRANSPORT_TREATED_EXCHANGE_NAME, "", event);
		};
	}

}
