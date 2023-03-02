package com.chess;

import java.awt.*;

public class Knight extends Piece{

    public Knight(int player) {
        super(player,2);
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Board board){
        Piece[][] gameBoard = board.getGameBoard();
        Point selectedTile = board.getSelectedTile();

        if(piece.getPlayerID()==2){
            if(((row == selectedTile.x-2 && Math.abs(col-selectedTile.y) == 1)
                    || (row == selectedTile.x-1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+2 && Math.abs(col-selectedTile.y) == 1))
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1))
                return true;
        }
        else{
            if(((row == selectedTile.x-2 && Math.abs(col-selectedTile.y) == 1)
                    || (row == selectedTile.x-1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+2 && Math.abs(col-selectedTile.y) == 1))
                    && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2))
                return true;
        }
        return false;
    }
}
