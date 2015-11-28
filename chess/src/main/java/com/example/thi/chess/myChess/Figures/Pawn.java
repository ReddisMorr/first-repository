package com.example.thi.chess.myChess.Figures;


import com.example.thi.chess.myChess.Constants;
import com.example.thi.chess.myChess.FigureType;
import com.example.thi.chess.myChess.Move;

public class Pawn extends Figure implements Move {
    public Pawn(int x, int y, FigureType type) {
        super(x, y, type);
    }


    @Override
    public void move(int x, int y) throws Exception {
        if (this.figureType == FigureType.BLACK) {
            if ((this.y - y ==2&& this.isInitPosition!=false)||(this.y-y==1)) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        } else {
            if ((this.y - y ==-2&&this.isInitPosition!=false)||(this.y-y==-1)) {
                this.x = x;
                this.y = y;
                this.isInitPosition = false;
            } else {
                throw new Exception(Constants.ILLEGAL_MOVE_MSG);
            }
        }
    }
}
