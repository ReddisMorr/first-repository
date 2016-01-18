package com.example.thi.circles;


public class CircleHolder {
    private float x;
    private float y;
    private boolean hit=false;

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public float getX() {
        return x;
    }

    public boolean isHit() {
        return hit;
    }

    public float getY() {
        return y;
    }
}
