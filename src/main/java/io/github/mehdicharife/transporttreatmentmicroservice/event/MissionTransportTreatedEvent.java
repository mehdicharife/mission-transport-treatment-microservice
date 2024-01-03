package io.github.mehdicharife.transporttreatmentmicroservice.event;



public class MissionTransportTreatedEvent {

    private Long missionId;

    private Long requestId;

    private Long professorId;


    public MissionTransportTreatedEvent() {

    }

    public MissionTransportTreatedEvent(Long missionId, Long requestId) {
        this.missionId = missionId;
        this.requestId = requestId;
    }


    public MissionTransportTreatedEvent(Long missionId, Long requestId, Long professorId) {
        this.missionId = missionId;
        this.requestId = requestId;
        this.professorId = professorId;
    }



    public Long getMissionId() {
        return this.missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }


    public Long getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    
}
