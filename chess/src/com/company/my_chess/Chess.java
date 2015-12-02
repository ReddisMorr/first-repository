package com.company.my_chess;

import com.company.my_chess.Figures.Bishop;
import com.company.my_chess.Figures.Figure;
import com.company.my_chess.Figures.Pawn;

import java.util.ArrayList;
import java.util.List;


public class Chess {


    private List<Figure> whites;
    private List<Figure> blacks;
    private FigureType currentTurn = FigureType.WHITE;
    private Figure[][] board = new Figure[8][8];

    private final static int MIN_X = 1;
    private final static int MIN_Y = 1;
    private final static int MAX_X = 8;
    private final static int MAX_Y = 8;

    public Chess() {
    }

    public void startGame() {
        init();
    }


    public void endTurn() {
        currentTurn = currentTurn == FigureType.WHITE ? currentTurn.BLACK : currentTurn.WHITE;
    }

    public Figure pickFigure(int x, int y) {
        if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y)
            return null;
        Figure figure = findByCoordinate(x, y);
        if (figure == null || figure.getFigureType() != currentTurn)
            return null;
        return figure;
    }

    public void makeMove(Figure fg, int y, int x) {
        try {
            int xOld = fg.getX();
            int yOld = fg.getY();
            fg.move(x, y);
            board[b2mX(xOld)][b2mY(yOld)] = null;
            board[b2mX(fg.getX())][b2mY(fg.getY())] = fg;
            Figure anotherFigure = findByCoordinate(x, y);
            if (anotherFigure != null && anotherFigure.getFigureType() != fg.getFigureType()) {
                anotherFigure.setIsKilled(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        whites = new ArrayList<>();
        blacks = new ArrayList<>();
        Pawn pawn1 = new Pawn(1, 2, FigureType.WHITE);
        Pawn pawn2 = new Pawn(2, 2, FigureType.WHITE);
        Pawn pawn3 = new Pawn(3, 2, FigureType.WHITE);
        Bishop bishop1 = new Bishop(1, 3, FigureType.WHITE);
        Bishop bishop2 = new Bishop(1, 6, FigureType.WHITE);
        whites.add(pawn1);
        whites.add(pawn2);
        whites.add(pawn3);
        whites.add(bishop1);
        whites.add(bishop2);
        for (Figure figure : whites) {
            board[b2mX(figure.getX())][b2mY(figure.getY())] = figure;
        }
        for (Figure figure : blacks) {
            board[b2mX(figure.getX())][b2mY(figure.getY())] = figure;
        }
    }


    private void validateNewPosition() {}


    private int b2mX(int x) {
        return x - 8;
    }

    private int b2mY(int y) {
        return y - 1;
    }

    private int m2bX(int x) {
        return x + 8;
    }

    private int m2bY(int y) {
        return y + 1;
    }

    private Figure findByCoordinate(int x, int y) {
        return board[b2mX(x)][b2mY(y)];
    }


    private void isCheck() {}

    private void isCheckmate() {}
}
