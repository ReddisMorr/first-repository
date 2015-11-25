package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

import java.lang.Exception;
import java.lang.Math;

/**
 * Created by teacher on 23.11.2015.
 * Король
 */
public class King extends Figure implements Move {
    public King(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y, FigureTipe type)throws Exception{
        if(this.y!=y||this.x!=x){ // здесь должно быть условие, что король не может ступать на клетки, которые могут быть биты вражескими фигурами, но для этого нужно вводить какую-нибудь буленовую характеристику клеток, которая будет показывать можно ли на нее ступать королю( а я не могу понять, где этоследует прописать и при каких условиях она должна помечаться)
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }

}