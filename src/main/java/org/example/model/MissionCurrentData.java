package org.example.model;

public class MissionCurrentData {
    int seq;// current mission item number
    int total; // total number of mission items
    int mission_state;
    int mission_mode;
    int mission_id;
    int fence_id;
    int rally_points_id;

    public MissionCurrentData(int seq, int total, int mission_state, int mission_mode, int mission_id, int fence_id, int rally_points_id) {
        this.seq = seq;
        this.total = total;
        this.mission_state = mission_state;
        this.mission_mode = mission_mode;
        this.mission_id = mission_id;
        this.fence_id = fence_id;
        this.rally_points_id = rally_points_id;
    }
}