package org.example.dataservice;

import org.example.model.AircraftState;
import org.example.model.MissionCurrentData;

public class DataService {
    public static AircraftState AIRCRAFT;
    public void initData() {
       this.AIRCRAFT=new AircraftState(1);
       initMissionCurrentData();
    }

    void initMissionCurrentData(){
        MissionCurrentData currentMissionData=new MissionCurrentData(1,1,1,1,1,1,1);
        AIRCRAFT.setCurrentMission(currentMissionData);
    }

}
