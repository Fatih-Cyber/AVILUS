package org.example.dataservice;

import org.example.model.AircraftState;
import org.example.model.MissionCurrentData;
import org.example.model.MissionItemData;

import java.util.ArrayList;

public class DataService {
    public static AircraftState AIRCRAFT;
    public void initData() {
       this.AIRCRAFT=new AircraftState();
       initMissionCurrentData();
       initMissionItemList();
    }

    void initMissionCurrentData(){
        MissionCurrentData currentMissionData=new MissionCurrentData(1,4,1,1,1,1,1);
        AIRCRAFT.setCurrentMission(currentMissionData);
    }
    void initMissionItemList(){
        ArrayList<MissionItemData> missionItemDataList=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
          missionItemDataList.add(new MissionItemData(i,0,0,1,i,i+1,i+2));
        }
        AIRCRAFT.setMissionItemsList(missionItemDataList);
    }

}
