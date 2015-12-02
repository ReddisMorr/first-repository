package com.company.my_chess.Figures;

import com.company.my_chess.FigureType;
import com.company.my_chess.Move;


public abstract class Figure implements Move {
    protected int x;
    protected int y;
    protected FigureType figureType;
    protected boolean isInitPosition = true;
    protected boolean isKilled = false;


    public Figure(int x, int y, FigureType figureType) {
        this.x = x;
        this.y = y;
        this.figureType = figureType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public boolean isInitPosition() {
        return isInitPosition;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setIsKilled(boolean isKilled) {
        this.isKilled = isKilled;
    }
}

