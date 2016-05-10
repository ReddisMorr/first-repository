package com.example.thi.championship.model;

import android.content.Context;

import com.example.thi.championship.DBHelper;

/**
 * Created by THI on 05.05.2016.
 */
public class Match {
    private long id;
    private long team1id;
    private long team2id;
    private String startTime;
    private long winnerId;
    private DBHelper dbHelper;

    public Match(Context context) {
        dbHelper = new DBHelper(context);
    }

    public Match(Context context, long team1id, long team2id, String startTime) {
        dbHelper = new DBHelper(context);
        this.team1id = team1id;
        this.team2id = team2id;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        String out = dbHelper.findTeamById(team1id).getName() + " versus(slovospb) " + dbHelper.findTeamById(team2id).getName() + " (" + startTime + ")";
        if (team1id == winnerId) out = dbHelper.findTeamById(team1id).getName() + "(W) versus(slovospb) " + dbHelper.findTeamById(team2id).getName() + " (" + startTime + ")";
        if (team2id == winnerId) out = dbHelper.findTeamById(team1id).getName() + " versus(slovospb) " + dbHelper.findTeamById(team2id).getName() + "(W) (" + startTime + ")";
        return out;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTeam1id() {
        return team1id;
    }
    public void setTeam1id(long team1id) {
        this.team1id = team1id;
    }
    public long getTeam2id() {
        return team2id;
    }
    public void setTeam2id(long team2id) {
        this.team2id = team2id;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public long getWinnerId() {
        return winnerId;
    }
    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }
}
