package com.chess;

import java.awt.*;

public class Piece {
    private int playerID;
    private int pieceID;

    public Piece(int player, int piece){
        playerID = player;
        pieceID = piece;
    }

    public int getPlayerID(){
        return playerID;
    }

    public int getPieceID(){
        return pieceID;
    }

    public boolean isValidMove(int row, int col, int pieceX, int pieceY, Board board){
        return false;
    }
    public boolean isValidMoveModified(int row, int col, int pieceX, int pieceY, Board board){
        return false;
    }
    public void setHasMoved(){}
}
