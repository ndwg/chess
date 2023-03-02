package com.chess;

public class Move {
    private Piece piece;
    private int rowDelta;
    private int columnDelta;

    public Move(Piece p, int r, int c){
        piece = p;
        rowDelta = r;
        columnDelta = c;
    }

    public Piece getPiece(){
        return piece;
    }

    public int getRowDelta(){
        return rowDelta;
    }

    public int getColumnDelta(){
        return columnDelta;
    }
}
