package com.company.my_chess.Figures;


import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;

public class Bishop extends Figure implements Move {

    public Bishop(int x, int y, FigureType type) {
        super(x, y, type);
    }

    public void move(int x, int y) throws Exception {
        if (Math.abs(this.x - x) == Math.abs(this.y - y)) {
            this.x = x;
            this.y = y;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }
    }
}

