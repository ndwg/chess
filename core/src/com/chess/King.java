package com.chess;

import java.awt.*;

public class King extends Piece{

    public King(int player) {
        super(player,6);
    }

    @Override
    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board){
        Piece[][] gameBoard = board.getGameBoard();

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(gameBoard[i][j] != null && gameBoard[i][j].getPlayerID() == 1){
                        if(gameBoard[i][j].getPieceID() == 6){
                            if(gameBoard[i][j].isValidMoveModified(row,col,i,j,board)) return false;
                        }
                        else if(gameBoard[i][j].isValidMove(row,col,i,j,board)) return false;
                    }
                }
            }
            if((Math.abs(row-pieceX) <= 1 && Math.abs(col-pieceY) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1))
                return true;
        }
        else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(gameBoard[i][j] != null && gameBoard[i][j].getPlayerID() == 2){
                        if(gameBoard[i][j].getPieceID() == 6){
                            if(gameBoard[i][j].isValidMoveModified(row,col,i,j,board)) return false;
                        }
                        else if(gameBoard[i][j].isValidMove(row,col,i,j,board)) return false;
                    }
                }
            }
            if((Math.abs(row-pieceX) <= 1 && Math.abs(col-pieceY) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2))
                return true;
        }
        return false;
    }

    @Override
    public boolean isValidMoveModified(int row, int col, int pieceX, int pieceY, Board board){
        Piece[][] gameBoard = board.getGameBoard();

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            if((Math.abs(row-pieceX) <= 1 && Math.abs(col-pieceY) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1))
                return true;
        }
        else{
            if((Math.abs(row-pieceX) <= 1 && Math.abs(col-pieceY) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2))
                return true;
        }
        return false;
    }
}
