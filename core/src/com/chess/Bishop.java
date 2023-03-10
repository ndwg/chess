package com.chess;

import java.awt.*;

public class Bishop extends Piece{

    public Bishop(int player) {
        super(player,3);
    }

    @Override
    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board){
        Piece[][] gameBoard = board.getGameBoard();

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            if(Math.abs(row-pieceX)==Math.abs(col-pieceY)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1)) {
                if(row < pieceX) row++;
                else row--;

                if(col < pieceY) col++;
                else col--;

                while(row != pieceX){
                    if(gameBoard[row][col] != null) return false;

                    if(row < pieceX) row++;
                    else row--;

                    if(col < pieceY) col++;
                    else col--;
                }
                return true;
            }
        }
        else{
            if(Math.abs(row-pieceX)==Math.abs(col-pieceY)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2)) {
                if(row < pieceX) row++;
                else row--;

                if(col < pieceY) col++;
                else col--;

                while(row != pieceX){
                    if(gameBoard[row][col] != null) return false;

                    if(row < pieceX) row++;
                    else row--;

                    if(col < pieceY) col++;
                    else col--;
                }
                return true;
            }
        }
        return false;
    }
}
