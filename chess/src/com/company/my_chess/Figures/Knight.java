package com.company.my_chess.Figures;

import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;


public class Knight extends Figure implements Move {
    public Knight(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y)throws Exception{
        if((Math.abs(this.y-y)==2&& Math.abs(this.x-x)==1)||(Math.abs(this.y-y)==1&& Math.abs(this.x-x)==2)){
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }

}

