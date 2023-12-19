package io.github.mehdicharife.transporttreatmentmicroservice.event;

public class MissionRequestApprovedEvent {

    private Long requestId;

    
    private Long missionId;


    public Long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getMissionId() {
        return this.missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    
}
