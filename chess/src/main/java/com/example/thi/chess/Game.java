package com.example.thi.chess;


import com.example.thi.chess.myChess.Chess;
import com.example.thi.chess.myChess.Figures.Figure;

public class Game {

    public static void main(String[] args) {
        Figure figure = null;
        Chess chess = new Chess();
        chess.startGame();
        figure = chess.pickFigure(1, 1);
        chess.makeMove(figure, 2, 2);
        chess.endTurn();
        figure = chess.pickFigure(7, 7);
        chess.makeMove(figure, 6, 6);
        chess.endTurn();
    }
}