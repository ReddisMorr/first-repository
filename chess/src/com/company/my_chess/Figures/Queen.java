package com.company.my_chess.Figures;

import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;

/**
 * Created by THI on 02.12.2015.
 */
public class Queen extends Figure implements Move {
    public Queen(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y)throws Exception{
        if(Math.abs(this.y-y)==Math.abs(this.x-x)||(this.x-x==0)||(this.y-y==0)){
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }

}

