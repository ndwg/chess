package com.chess;

import java.awt.*;

public class Pawn extends Piece{
    private boolean hasMoved;

    public Pawn(int player) {
        super(player,1);
        hasMoved = false;
    }

    @Override
    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board){
        Piece[][] gameBoard = board.getGameBoard();
        Move lastMove = board.getLastMove();

        if(row < 0 || row > 7 || col < 0 || col > 7) return false;

        if(gameBoard[pieceX][pieceY].getPlayerID()==2){
            if(lastMove.getPiece() != null && lastMove.getPiece().getPieceID() == 1 && lastMove.getPiece().getPlayerID() != 2 && gameBoard[row+1][col] == lastMove.getPiece() && lastMove.getRowDelta() == 2
                    && Math.abs(col-pieceY) == 1 && row == pieceX-1){
                board.enPassantCleanUp(row+1,col);
                return true;
            }
            else if(row == pieceX-2 && col == pieceY && gameBoard[row][col] == null && !hasMoved) {
                return true;
            }
            else if(row == pieceX-1 && col == pieceY && gameBoard[row][col] == null) {
                return true;
            }
            else if(gameBoard[row][col] == null) return false;
            else if((col == pieceY+1 || col == pieceY-1) && row == pieceX-1 && gameBoard[row][col].getPlayerID() == 1) {
                return true;
            }
        }
        else{
            if(lastMove.getPiece() != null && lastMove.getPiece().getPieceID() == 1 && lastMove.getPiece().getPlayerID() != 1 && gameBoard[row-1][col] == lastMove.getPiece() && lastMove.getRowDelta() == 2
                    && Math.abs(col-pieceY) == 1 && row == pieceX+1){
                board.enPassantCleanUp(row-1,col);
                return true;
            }
            else if(row == pieceX+2 && col == pieceY && gameBoard[row][col] == null && !hasMoved) {
                return true;
            }
            else if(row == pieceX+1 && col == pieceY && gameBoard[row][col] == null) {
                return true;
            }
            else if(gameBoard[row][col] == null) return false;
            else if((col == pieceY+1 || col == pieceY-1) && row == pieceX+1 && gameBoard[row][col].getPlayerID() == 2) {
                return true;
            }
        }

        return false;
    }

    public void setHasMoved(){
        hasMoved = true;
    }
}
