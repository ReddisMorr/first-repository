package com.company.my_chess.Figures;

import com.company.my_chess.Constants;
import com.company.my_chess.FigureType;
import com.company.my_chess.Move;



public class Rook extends Figure implements Move {
    public Rook(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y)throws Exception{
        if((this.x-x==0&& this.y-y!=0)||(this.y-y==0&&this.x-x!=0)){
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }


}

