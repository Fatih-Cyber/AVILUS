package org.example.model;

import java.util.ArrayList;

public class AircraftState {
    private ArrayList<MissionItemData> missionItemsList;
    private MissionCurrentData currentMissionData;
    private float latitude;
    private float longitude;
    private float altitude;
    private float speed;
    private boolean isOnGround;
    private boolean isPowerOn;
    private boolean isSystemActive;
    private int missionId;
    private int systemId;
    private int missionSeq;

    public boolean isCurrentMissionCompleted() {
        return isCurrentMissionCompleted;
    }

    public void setCurrentMissionCompleted(boolean currentMissionCompleted) {
        isCurrentMissionCompleted = currentMissionCompleted;
    }

    private boolean isCurrentMissionCompleted;

    public void flyToNextWayPoint(){
        if (this.missionSeq<this.getCurrentMission().getTotal()){
            this.missionSeq=+1;
        }else setCurrentMissionCompleted(true);

        // broadCast MISSION_ITEM_REACHED
    }
    public int numberOfWayPoints(){return getMissionItemsList().size();};
    public ArrayList<MissionItemData> getMissionItemsList() {
        return missionItemsList;
    }

    public void setMissionItemsList(ArrayList<MissionItemData> missionItemsList) {
        this.missionItemsList = missionItemsList;
    }

    public MissionCurrentData getCurrentMission() {
        return currentMissionData;
    }

    public void setCurrentMission(MissionCurrentData currentMissionData) {
        this.currentMissionData = currentMissionData;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public boolean isPowerOn() {
        return isPowerOn;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }

    public boolean isSystemActive() {
        return isSystemActive;
    }

    public void setSystemActive(boolean systemActive) {
        isSystemActive = systemActive;
    }

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getMissionSeq() {
        return missionSeq;
    }

    public void setMissionSeq(int missionSeq) {
        this.missionSeq = missionSeq;
    }
}
