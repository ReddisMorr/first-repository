package com.example.thi.chess.myChess.Figures;


import com.example.thi.chess.myChess.Constants;
import com.example.thi.chess.myChess.FigureType;
import com.example.thi.chess.myChess.Move;

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
