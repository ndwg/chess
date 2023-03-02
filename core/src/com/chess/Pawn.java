package com.chess;

import java.awt.*;

public class Pawn extends Piece{
    private boolean hasMoved;

    public Pawn(int player) {
        super(player,1);
        hasMoved = false;
    }

    @Override
    public boolean isValidMove(int row, int col, Piece piece, Board board){
        Piece[][] gameBoard = board.getGameBoard();
        Point selectedTile = board.getSelectedTile();
        Move lastMove = board.getLastMove();

        if(piece.getPlayerID()==2){
            if(lastMove.getPiece() != null && lastMove.getPiece().getPieceID() == 1 && lastMove.getPiece().getPlayerID() != 2 && gameBoard[row+1][col] == lastMove.getPiece() && lastMove.getRowDelta() == 2
                    && Math.abs(col-selectedTile.y) == 1 && row == selectedTile.x-1){
                board.enPassantCleanUp(row+1,col);
                return true;
            }
            else if(row == selectedTile.x-2 && col == selectedTile.y && gameBoard[row][col] == null && !hasMoved) {
                hasMoved = true;
                return true;
            }
            else if(row == selectedTile.x-1 && col == selectedTile.y && gameBoard[row][col] == null) {
                hasMoved = true;
                return true;
            }
            else if(gameBoard[row][col] == null) return false;
            else if((col == selectedTile.y+1 || col == selectedTile.y-1) && row == selectedTile.x-1 && gameBoard[row][col].getPlayerID() == 1) {
                hasMoved = true;
                return true;
            }
        }
        else{
            if(lastMove.getPiece() != null && lastMove.getPiece().getPieceID() == 1 && lastMove.getPiece().getPlayerID() != 1 && gameBoard[row-1][col] == lastMove.getPiece() && lastMove.getRowDelta() == 2
                    && Math.abs(col-selectedTile.y) == 1 && row == selectedTile.x+1){
                board.enPassantCleanUp(row-1,col);
                return true;
            }
            else if(row == selectedTile.x+2 && col == selectedTile.y && gameBoard[row][col] == null && !hasMoved) {
                hasMoved = true;
                return true;
            }
            else if(row == selectedTile.x+1 && col == selectedTile.y && gameBoard[row][col] == null) {
                hasMoved = true;
                return true;
            }
            else if(gameBoard[row][col] == null) return false;
            else if((col == selectedTile.y+1 || col == selectedTile.y-1) && row == selectedTile.x+1 && gameBoard[row][col].getPlayerID() == 2) {
                hasMoved = true;
                return true;
            }
        }

        return false;
    }
}
