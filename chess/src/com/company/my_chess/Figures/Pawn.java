package com.company.my_chess.Figures;


import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;

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

