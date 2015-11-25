package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

import java.lang.Exception;
import java.lang.Math;

/**
 * Created by teacher on 23.11.2015.
 * Ладья\ Турка
 */
public class Rook extends Figure implements Move {
    public Rook(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y, FigureTipe type)throws Exception{
        if((this.x-x==0&& this.y-y!=0)||(this.y-y==0&&this.x-x!=0)){
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }

}