package com.chess;

import java.awt.*;

public class Rook extends Piece{

    public Rook(int player) {
        super(player,4);
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Board board) {
        Piece[][] gameBoard = board.getGameBoard();
        Point selectedTile = board.getSelectedTile();

        if(piece.getPlayerID()==2){
            if((row == selectedTile.x || col == selectedTile.y) && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 1)) {
                if(row < selectedTile.x) row++;
                else if(row > selectedTile.x) row--;
                else if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != null) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                }

                while(col != selectedTile.y){
                    if(gameBoard[row][col] != null) return false;

                    else if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        else{
            if((row == selectedTile.x || col == selectedTile.y) && (gameBoard[row][col] == null || gameBoard[row][col].getPlayerID() == 2)) {
                if(row < selectedTile.x) row++;
                else if(row > selectedTile.x) row--;
                else if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != null) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                }

                while(col != selectedTile.y){
                    if(gameBoard[row][col] != null) return false;

                    else if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        return false;
    }
}
