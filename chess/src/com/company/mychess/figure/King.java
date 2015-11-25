package com.company.mychess.figure;

import com.company.mychess.FigureType;
import com.company.mychess.Constants;
import com.company.mychess.Move;

import java.lang.Exception;
import java.lang.Math;

/**
 * Created by teacher on 23.11.2015.
 * ������
 */
public class King extends Figure implements Move {
    public King(int x, int y, FigureType type) {
        super(x, y, type);
    }
    public void move(int x, int y, FigureTipe type)throws Exception{
        if(this.y!=y||this.x!=x){ // ����� ������ ���� �������, ��� ������ �� ����� ������� �� ������, ������� ����� ���� ���� ���������� ��������, �� ��� ����� ����� ������� �����-������ ��������� �������������� ������, ������� ����� ���������� ����� �� �� ��� ������� ������( � � �� ���� ������, ��� ���������� ��������� � ��� ����� �������� ��� ������ ����������)
            this.y=y;
            this.x=x;
        }else{
            throw new Exception(Constants.ILLEGAL_MOVE_MSG);
        }

    }

}