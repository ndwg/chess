package com.chess;

import java.awt.*;

public class Knight extends Piece{

    public Knight(int player) {
        super(player,2);
    }

    @Override
    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board){
        Piece[][] gameBoard = board.getGameBoard();

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            if(((row == pieceX-2 && Math.abs(col-pieceY) == 1)
                    || (row == pieceX-1 && Math.abs(col-pieceY) == 2)
                    || (row == pieceX+1 && Math.abs(col-pieceY) == 2)
                    || (row == pieceX+2 && Math.abs(col-pieceY) == 1))
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1))
                return true;
        }
        else{
            if(((row == pieceX-2 && Math.abs(col-pieceY) == 1)
                    || (row == pieceX-1 && Math.abs(col-pieceY) == 2)
                    || (row == pieceX+1 && Math.abs(col-pieceY) == 2)
                    || (row == pieceX+2 && Math.abs(col-pieceY) == 1))
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2))
                return true;
        }
        return false;
    }
}
