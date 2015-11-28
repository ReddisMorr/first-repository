package com.example.thi.chess.myChess.Figures;

import com.example.thi.chess.myChess.Constants;
import com.example.thi.chess.myChess.FigureType;
import com.example.thi.chess.myChess.Move;


public class King extends Figure implements Move {
    public King(int x, int y, FigureType type) {
        super(x, y, type);
    }

    public void move(int x, int y) throws Exception {
        if (this.y != y || this.x != x) {
            this.y = y;
            this.x = x;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }
}
