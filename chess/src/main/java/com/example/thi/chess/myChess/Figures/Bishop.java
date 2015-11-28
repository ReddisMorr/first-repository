package com.example.thi.chess.myChess.Figures;

import com.example.thi.chess.myChess.Constants;
import com.example.thi.chess.myChess.FigureType;
import com.example.thi.chess.myChess.Move;

/**
 * Created by THI on 28.11.2015.
 */
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
