package com.pojo;

public class TrackResult {

    private String track;
    private String status;

    public TrackResult(String track, String status) {
        this.track = track;
        this.status = status;
    }

    @Override
    public String toString() {
        return track;
    }
}
