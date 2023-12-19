package io.github.mehdicharife.transporttreatmentmicroservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.Mission;
import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;
import io.github.mehdicharife.transporttreatmentmicroservice.event.MissionRequestApprovedEvent;
import io.github.mehdicharife.transporttreatmentmicroservice.service.MissionTransportProcessor;
import io.github.mehdicharife.transporttreatmentmicroservice.service.MissionTransportTreatmentService;


@Component
@RabbitListener(queues = "mission-request-approved-transport-queue")
public class MissionRequestApprovedEventListener {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MissionTransportProcessor missionTransportProcessor;

    @Autowired
    private MissionTransportTreatmentService missionTransportTreatmentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RabbitHandler
    public void react(MissionRequestApprovedEvent event) {
        Mission mission = restTemplate.getForObject("http://localhost:8089/esb/missions/" + event.getMissionId(),
         Mission.class
        );

        MissionTransportTreatment treatment = missionTransportProcessor.processTransport(mission);
        missionTransportTreatmentService.saveMissionTransportTreatment(treatment);

        rabbitTemplate.convertAndSend("mission-transport-treated", "", treatment);
    }
    
}
