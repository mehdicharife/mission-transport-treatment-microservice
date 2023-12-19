package io.github.mehdicharife.transporttreatmentmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.mehdicharife.transporttreatmentmicroservice.domain.MissionTransportTreatment;

@Repository
public interface MissionTransportTreatmentRepository extends JpaRepository<MissionTransportTreatment, Long> {
    
}
