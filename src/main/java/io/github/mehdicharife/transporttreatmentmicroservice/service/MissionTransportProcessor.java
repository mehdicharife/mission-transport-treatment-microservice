package io.github.mehdicharife.transporttreatmentmicroservice.service;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.Mission;
import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;

public interface MissionTransportProcessor {
    MissionTransportTreatment processTransport(Mission mission);
}
