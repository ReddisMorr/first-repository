package com.company.my_chess.Figures;

import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;

public class King extends Figure implements Move {
    public King(int x, int y, FigureType type) {
        super(x, y, type);
    }

    public void move(int x, int y) throws Exception {
        if ( Math.abs(this.y-y)==1 || Math.abs(this.x-x)==1 ){
            this.y = y;
            this.x = x;
        } else {
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }
}
