package com.chess;

import java.awt.*;

public class Bishop extends Piece{
    private int pieceID = 3;

    public Bishop(int player) {
        super(player,3);
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Point selectedTile, Piece[][] gameBoard){
        if(piece.getPlayerID()==2){
            if(Math.abs(row-selectedTile.x)==Math.abs(col-selectedTile.y)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1)) {
                if(row < selectedTile.x) row++;
                else row--;

                if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != null) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                    if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        else{
            if(Math.abs(row-selectedTile.x)==Math.abs(col-selectedTile.y)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2)) {
                if(row < selectedTile.x) row++;
                else row--;

                if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != null) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                    if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        return false;
    }
}
