package io.github.mehdicharife.transporttreatmentmicroservice.service;

import org.springframework.stereotype.Service;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;
import io.github.mehdicharife.transporttreatmentmicroservice.repository.MissionTransportTreatmentRepository;

@Service
public class MissionTransportTreatmentServiceImpl implements MissionTransportTreatmentService{

    private MissionTransportTreatmentRepository repository;


    public MissionTransportTreatmentServiceImpl(MissionTransportTreatmentRepository repository) {
        this.repository = repository;
    }

    public MissionTransportTreatment saveMissionTransportTreatment(MissionTransportTreatment missionTransportTreatment) {
        return this.repository.save(missionTransportTreatment);
    }
}
