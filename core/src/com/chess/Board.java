package com.chess;

import java.awt.*;

public class Board {
    private Piece[][] gameBoard;
    private Point selectedTile, toBePromoted;
    private Move lastMove;
    public Board(){
        gameBoard = new Piece[8][8];
        selectedTile = new Point(1000,1000);
        lastMove = new Move(null,0,0);
        toBePromoted = null;
        setBoard();
    }

    public void setBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    if(j == 0 || j == 7) gameBoard[i][j] = new Rook(1);
                    else if(j == 1 || j == 6) gameBoard[i][j] = new Knight(1);
                    else if(j == 2 || j == 5) gameBoard[i][j] = new Bishop(1);
                    else if(j == 3) gameBoard[i][j] = new Queen(1);
                    else gameBoard[i][j] = new King(1);
                }
                else if(i == 1) gameBoard[i][j] = new Pawn(1);
                else if(i == 6) gameBoard[i][j] = new Pawn(2);
                else if(i == 7){
                    if(j == 0 || j == 7) gameBoard[i][j] = new Rook(2);
                    else if(j == 1 || j == 6) gameBoard[i][j] = new Knight(2);
                    else if(j == 2 || j == 5) gameBoard[i][j] = new Bishop(2);
                    else if(j == 3) gameBoard[i][j] = new Queen(2);
                    else gameBoard[i][j] = new King(2);
                }
                else gameBoard[i][j] = null;
            }
        }
    }

    public Piece[][] getGameBoard(){
        return gameBoard;
    }

    public Piece getTile(int row, int col){
        return gameBoard[row][col];
    }

    public void selectTile(int row, int col){
        if(row == selectedTile.x && col == selectedTile.y) selectedTile.setLocation(1000,1000);
        else if(selectedTile.x != 1000 && gameBoard[selectedTile.x][selectedTile.y] != null) updateBoard(row,col);
        else selectedTile.setLocation(row,col);
    }

    public Point getSelectedTile(){
        return selectedTile;
    }

    public Move getLastMove() {
        return lastMove;
    }

    public Point getToBePromoted(){return toBePromoted;}

    public void promoteToKnight(){
        gameBoard[toBePromoted.x][toBePromoted.y] = new Knight(gameBoard[toBePromoted.x][toBePromoted.y].getPlayerID());
        toBePromoted = null;
    }

    public void promoteToBishop(){
        gameBoard[toBePromoted.x][toBePromoted.y] = new Bishop(gameBoard[toBePromoted.x][toBePromoted.y].getPlayerID());
        toBePromoted = null;
    }

    public void promoteToRook(){
        gameBoard[toBePromoted.x][toBePromoted.y] = new Rook(gameBoard[toBePromoted.x][toBePromoted.y].getPlayerID());
        toBePromoted = null;
    }

    public void promoteToQueen(){
        gameBoard[toBePromoted.x][toBePromoted.y] = new Queen(gameBoard[toBePromoted.x][toBePromoted.y].getPlayerID());
        toBePromoted = null;
    }

    private void updateBoard(int row, int col){
        Piece p = getTile(selectedTile.x,selectedTile.y);

        if(p.isValidMove(row,col, selectedTile.x, selectedTile.y,this) && checkReverseCheck(row,col)){
            lastMove = new Move(p,Math.abs(row-selectedTile.x),Math.abs(col-selectedTile.y));

            if(p.getPieceID() == 1) p.setHasMoved();

            gameBoard[row][col] = gameBoard[selectedTile.x][selectedTile.y];
            gameBoard[selectedTile.x][selectedTile.y] = null;
            selectedTile.setLocation(1000,1000);
        }
        else selectedTile.setLocation(row,col);

        checkPromotion();
    }

    public void enPassantCleanUp(int row, int col){
        gameBoard[row][col] = null;
    }

    private void checkPromotion(){
        for(int i = 0; i < 8; i++){
            if(gameBoard[0][i] != null && gameBoard[0][i].getPieceID() == 1) toBePromoted = new Point(0,i);
            else if(gameBoard[7][i] != null && gameBoard[7][i].getPieceID() == 1) toBePromoted = new Point(7,i);
        }
    }

    /*private Point locateKing(int pID){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(gameBoard[i][j].getPieceID() == 6 && gameBoard[i][j].getPlayerID() == pID) return new Point(i,j);
            }
        }
        return null;
    }*/

    private boolean checkReverseCheck(int row, int col){
        Piece p = getTile(selectedTile.x,selectedTile.y);
        Piece[][] holdBoard = new Piece[8][8];
        Point kingCoordinates = null;
        int opponentID;

        if(p.getPieceID() == 6) return true;

        if(p.getPlayerID() == 2) opponentID = 1;
        else opponentID = 2;

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                holdBoard[i][j] = gameBoard[i][j];
                if(gameBoard[i][j] != null && gameBoard[i][j].getPieceID() == 6 && gameBoard[i][j].getPlayerID() == p.getPlayerID()) kingCoordinates = new Point(i,j);
            }
        }

        gameBoard[row][col] = gameBoard[selectedTile.x][selectedTile.y];
        gameBoard[selectedTile.x][selectedTile.y] = null;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameBoard[i][j] != null && gameBoard[i][j].getPlayerID() == opponentID){
                    if(gameBoard[i][j].getPieceID() == 6){
                        if(gameBoard[i][j].isValidMoveModified(kingCoordinates.x, kingCoordinates.y, i,j,this)) {
                            for(int k = 0; k < 8; k++) {
                                System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                            }
                            return false;
                        }
                    }
                    else if(gameBoard[i][j].isValidMove(kingCoordinates.x, kingCoordinates.y,i,j,this)) {
                        for(int k = 0; k < 8; k++) {
                            System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                        }
                        return false;
                    }
                }
            }
        }

        for(int k = 0; k < 8; k++) {
            System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
        }

        return true;
    }
}
