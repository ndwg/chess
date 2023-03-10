package com.chess;

import java.awt.*;

public class Queen extends Piece{

    public Queen(int player) {
        super(player,5);
    }

    @Override
    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board) {
        Piece[][] gameBoard = board.getGameBoard();

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            if((row == pieceX || col == pieceY) && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1)) {
                if(row < pieceX) row++;
                else if(row > pieceX) row--;
                else if(col < pieceY) col++;
                else col--;

                while(row != pieceX){
                    if(gameBoard[row][col] != null) return false;

                    if(row < pieceX) row++;
                    else row--;

                }

                while(col != pieceY){
                    if(gameBoard[row][col] != null) return false;

                    else if(col < pieceY) col++;
                    else col--;
                }
                return true;
            }
            else if(Math.abs(row-pieceX)==Math.abs(col-pieceY)
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
            if((row == pieceX || col == pieceY) && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2)) {
                if(row < pieceX) row++;
                else if(row > pieceX) row--;
                else if(col < pieceY) col++;
                else col--;

                while(row != pieceX){
                    if(gameBoard[row][col] != null) return false;

                    if(row < pieceX) row++;
                    else row--;

                }

                while(col != pieceY){
                    if(gameBoard[row][col] != null) return false;

                    else if(col < pieceY) col++;
                    else col--;
                }
                return true;
            }
            else if(Math.abs(row-pieceX)==Math.abs(col-pieceY)
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
