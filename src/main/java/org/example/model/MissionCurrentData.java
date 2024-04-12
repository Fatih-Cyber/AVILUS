package org.example.model;

public class MissionCurrentData {
    private int seq;// current mission item number
    private int total; // total number of mission items
    private int mission_state;
    private int mission_mode;
    private int mission_id;
    private int fence_id;
    private int rally_points_id;

    public MissionCurrentData(int seq, int total, int mission_state, int mission_mode, int mission_id, int fence_id, int rally_points_id) {
        this.seq = seq;
        this.total = total;
        this.mission_state = mission_state;
        this.mission_mode = mission_mode;
        this.mission_id = mission_id;
        this.fence_id = fence_id;
        this.rally_points_id = rally_points_id;
    }

    public int getSeq() {
        return seq;
    }

    public int getTotal() {
        return total;
    }

    public int getMission_state() {
        return mission_state;
    }

    public int getMission_mode() {
        return mission_mode;
    }

    public int getMission_id() {
        return mission_id;
    }

    public int getFence_id() {
        return fence_id;
    }

    public int getRally_points_id() {
        return rally_points_id;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setMission_state(int mission_state) {
        this.mission_state = mission_state;
    }

    public void setMission_mode(int mission_mode) {
        this.mission_mode = mission_mode;
    }

    public void setMission_id(int mission_id) {
        this.mission_id = mission_id;
    }

    public void setFence_id(int fence_id) {
        this.fence_id = fence_id;
    }

    public void setRally_points_id(int rally_points_id) {
        this.rally_points_id = rally_points_id;
    }
}