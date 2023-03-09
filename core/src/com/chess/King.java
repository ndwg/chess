package com.chess;

import java.awt.*;

public class King extends Piece{

    public King(int player) {
        super(player,6);
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Board board){
        Piece[][] gameBoard = board.getGameBoard();
        Point selectedTile = board.getSelectedTile();

        if(piece.getPlayerID()==2){
            if((Math.abs(row-selectedTile.x) <= 1 && Math.abs(col-selectedTile.y) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1))
                return true;
        }
        else{
            if((Math.abs(row-selectedTile.x) <= 1 && Math.abs(col-selectedTile.y) <= 1)
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2))
                return true;
        }
        return false;
    }
}
