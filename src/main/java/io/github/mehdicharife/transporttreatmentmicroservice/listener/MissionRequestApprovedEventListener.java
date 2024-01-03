package io.github.mehdicharife.transporttreatmentmicroservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.Mission;
import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;
import io.github.mehdicharife.transporttreatmentmicroservice.event.MissionTransportTreatedEvent;
import ma.ensias.missionrequestservice.event.MissionRequestApprovedEvent;
import io.github.mehdicharife.transporttreatmentmicroservice.service.MissionTransportProcessor;
import io.github.mehdicharife.transporttreatmentmicroservice.service.MissionTransportTreatmentService;


@Component
@RabbitListener(queues = "${missionRequestApprovedQueue}")
public class MissionRequestApprovedEventListener {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MissionTransportProcessor missionTransportProcessor;

    @Autowired
    private MissionTransportTreatmentService missionTransportTreatmentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${missionTransportTreatedTopic}")
    private String MISSION_TRANSPORT_TREATED_EXCHANGE_NAME;

    @Value("${esb}")
    private String esb;


    @RabbitHandler
    public void react(MissionRequestApprovedEvent event) {
        Mission mission = restTemplate.getForObject(esb + "missions/" + event.getMissionId(),
        Mission.class
        );


        MissionTransportTreatment treatment = missionTransportProcessor.processTransport(mission);
        missionTransportTreatmentService.saveMissionTransportTreatment(treatment);

        MissionTransportTreatedEvent missionTransportTreatedEvent = new MissionTransportTreatedEvent(
            mission.getId(), 
            event.getRequestId(),
            event.getProfessorId()
        );

        rabbitTemplate.convertAndSend(MISSION_TRANSPORT_TREATED_EXCHANGE_NAME, "", missionTransportTreatedEvent);
    }
    
}
