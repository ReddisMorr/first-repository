package com.example.bubblebreaker;

import java.io.Serializable;

/**
 * Created by THI on 18.04.2016.
 */
public class BBSettings implements Serializable {
  private int bubble_diameter;
  private int bubble_color;
  private boolean bomb;

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public int getBubble_diameter() {
        return bubble_diameter;
    }

    public void setBubble_diameter(int bubble_diameter) {
        this.bubble_diameter = bubble_diameter;
    }

    public int getBubble_color() {
        return bubble_color;
    }

    public void setBubble_color(int bubble_color) {
        this.bubble_color = bubble_color;
    }

    @Override
    public String toString() {
        return "BBSettings{" +
                "bubble_diameter=" + bubble_diameter +
                ", bubble_color=" + bubble_color +
                '}';
    }
}
