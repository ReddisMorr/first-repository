package com.example.thi.championship.model;

/**
 * Created by THI on 05.05.2016.
 */
public class Player {
    private long id;
    private String name;
    private int age;
    private long teamId;

    public Player(String name, int age, long teamId) {
        this.name = name;
        this.age = age;
        this.teamId = teamId;
    }
    public Player() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public long getTeamId() {
        return teamId;
    }
    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
