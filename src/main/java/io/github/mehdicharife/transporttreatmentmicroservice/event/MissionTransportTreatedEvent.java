package io.github.mehdicharife.transporttreatmentmicroservice.event;



public class MissionTransportTreatedEvent {

    private Long missionId;

    private Long requestId;


    public MissionTransportTreatedEvent() {

    }

    public MissionTransportTreatedEvent(Long missionId, Long requestId) {
        this.missionId = missionId;
        this.requestId = requestId;
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

    
}
