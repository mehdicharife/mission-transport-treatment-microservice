package io.github.mehdicharife.transporttreatmentmicroservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {

    @Value("${missionRequestApprovedQueue}")
    private String MISSION_REQUEST_APPROVED_QUEUE_NAME;

    @Value("${missionRequestApprovedTopic}")
    private String MISSION_REQUEST_APPROVED_EXCHANGE_NAME;

    @Value("${missionTransportTreatedTopic}")
    private String MISSION_TRANSPORT_TREATED_EXCHANGE_NAME;


    @Bean
    public Queue missionRequestApprovedQueue() {
        return new Queue(MISSION_REQUEST_APPROVED_QUEUE_NAME, true);
    }


    @Bean
    public Binding missionRequestApprovedExchangeBinding(Queue missionRequestApprovedQueue) {
        return new Binding(missionRequestApprovedQueue.getName(), 
            DestinationType.EXCHANGE,
            MISSION_REQUEST_APPROVED_EXCHANGE_NAME,
            "",
            null
        );
    }


    @Bean
    public FanoutExchange missionTransportTreatedExchange() {
        return new FanoutExchange(MISSION_TRANSPORT_TREATED_EXCHANGE_NAME, true, false);
    }


    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }

    
}