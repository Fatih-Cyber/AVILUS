package org.example.model;

import java.util.ArrayList;

public class AircraftState {
    private int numberOfWayPoints;
    private MissionCurrentData currentMissionData;

    public ArrayList<MissionItemData> getMissionItemsList() {
        return missionItemsList;
    }

    public void setMissionItemsList(ArrayList<MissionItemData> missionItemsList) {
        this.missionItemsList = missionItemsList;
    }

    private ArrayList<MissionItemData> missionItemsList;

    public MissionCurrentData getCurrentMission() {
        return currentMissionData;
    }

    public void setCurrentMission(MissionCurrentData currentMissionData) {
        this.currentMissionData = currentMissionData;
    }

    public AircraftState(int numberOfWayPoints) {
        this.numberOfWayPoints = numberOfWayPoints;
    }

    public int getNumberOfWayPoints() {
        return numberOfWayPoints;
    }

    public void setNumberOfWayPoints(int numberOfWayPoints) {
        this.numberOfWayPoints = numberOfWayPoints;
    }
}
