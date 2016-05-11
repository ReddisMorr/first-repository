package com.example.thi.championship.model;

import android.content.Context;

import com.example.thi.championship.DBHelper;

/**
 * Created by THI on 05.05.2016.
 */
public class Match {
    private long id;
    private Team team1=new Team();
    private Team team2=new Team();
    private String startTime;
    private long winnerId;


    public Match(long id, long team1, long team2, String startTime, long winnerId) {
        this.id = id;
        this.team1.setId(team1);
        this.team2.setId(team2);
        this.startTime = startTime;
        this.winnerId = winnerId;
    }


    public Match() {
    }

    public Match(Team team1, Team team2, String startTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        String out = team1.getName() + " versus(slovospb) " + team2.getName() + " (" + startTime + ")";
        if (team1.getId() == winnerId) out = team1.getName() + "(W) versus(slovospb) " + team2.getName() + " (" + startTime + ")";
        if (team2.getId() == winnerId) out = team1.getName() + " versus(slovospb) " + team2.getName() + "(W) (" + startTime + ")";
        return out;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTeam1id() {
        return team1.getId();
    }
    public void setTeam1id(long team1id) {
        this.team1.setId(team1id);
    }
    public long getTeam2id() {
        return team2.getId();
    }
    public void setTeam2id(long team2id) {
        this.team2.setId(team2id);
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

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
