package io.github.mehdicharife.transporttreatmentmicroservice.service;

import org.springframework.stereotype.Service;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.Mission;
import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;

@Service
public class MissionTransportProcessorImpl implements MissionTransportProcessor{
    
    public MissionTransportTreatment processTransport(Mission mission) {
        return new MissionTransportTreatment(Long.valueOf(12), Long.valueOf(10));
    }
}
