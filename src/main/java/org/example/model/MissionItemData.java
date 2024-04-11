package org.example.model;

import io.dronefleet.mavlink.common.MavCmd;
import io.dronefleet.mavlink.common.MavFrame;
import io.dronefleet.mavlink.common.MavMissionType;
import io.dronefleet.mavlink.util.EnumValue;

public class MissionItemData {
    private int targetSystem;
    private int targetComponent;
    private int seq;
    private int frame;
    private int command;
    private int current;
    private int autocontinue;
    private float param1;
    private float param2;
    private float param3;
    private float param4;
    private int x;
    private int y;
    private float z;
    private int missionType;

    public MissionItemData(int seq, int command, int current, int autocontinue, int x, int y, float z) {
        this.seq = seq;
        this.frame = frame;
        this.command = command;
        this.current = current;
        this.autocontinue = autocontinue;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
