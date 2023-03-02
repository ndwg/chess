package com.chess;

import java.awt.*;

public class Pawn extends Piece{
    private int pieceID = 1;

    public Pawn(int player) {
        super(player,1);
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Point tile, Piece[][] gameBoard){
        if(piece.getPlayerID()==2){
            if(row == tile.x-1 && col == tile.y && gameBoard[row][col] == null) return true;
            else if(gameBoard[row][col] == null) return false;
            else if((col == tile.y+1 || col == tile.y-1) && row == tile.x-1 && gameBoard[row][col].getPlayerID() == 1) return true;
        }
        else{
            if(row == tile.x+1 && col == tile.y && gameBoard[row][col] == null) return true;
            else if(gameBoard[row][col] == null) return false;
            else if((col == tile.y+1 || col == tile.y-1) && row == tile.x+1 && gameBoard[row][col].getPlayerID() == 2) return true;
        }

        return false;
    }
}
