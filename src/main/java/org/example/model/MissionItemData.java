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

    public int getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(int targetSystem) {
        this.targetSystem = targetSystem;
    }

    public int getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(int targetComponent) {
        this.targetComponent = targetComponent;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getAutocontinue() {
        return autocontinue;
    }

    public void setAutocontinue(int autocontinue) {
        this.autocontinue = autocontinue;
    }

    public float getParam1() {
        return param1;
    }

    public void setParam1(float param1) {
        this.param1 = param1;
    }

    public float getParam2() {
        return param2;
    }

    public void setParam2(float param2) {
        this.param2 = param2;
    }

    public float getParam3() {
        return param3;
    }

    public void setParam3(float param3) {
        this.param3 = param3;
    }

    public float getParam4() {
        return param4;
    }

    public void setParam4(float param4) {
        this.param4 = param4;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public int getMissionType() {
        return missionType;
    }

    public void setMissionType(int missionType) {
        this.missionType = missionType;
    }
}
