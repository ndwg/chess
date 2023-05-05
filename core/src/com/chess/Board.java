package com.chess;

import java.awt.*;

public class Board {
    private Piece[][] gameBoard;
    private Point selectedTile, toBePromoted;
    private Move lastMove;
    private boolean checkStatus;

    public Board(){
        gameBoard = new Piece[8][8];
        selectedTile = new Point(1000,1000);
        lastMove = new Move(null,0,0);
        toBePromoted = null;
        checkStatus = false;

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

        if(p.isValidMove(row,col, selectedTile.x, selectedTile.y,this) && checkReverseCheck(row,col,selectedTile.x,selectedTile.y)){
            lastMove = new Move(p,Math.abs(row-selectedTile.x),Math.abs(col-selectedTile.y));

            if(p.getPieceID() == 1 || p.getPieceID() == 4 || p.getPieceID() == 6) p.setHasMoved();

            gameBoard[row][col] = gameBoard[selectedTile.x][selectedTile.y];
            gameBoard[selectedTile.x][selectedTile.y] = null;
            selectedTile.setLocation(1000,1000);
        }
        else selectedTile.setLocation(row,col);

        checkPromotion();
        if(checkMate(p.getPlayerID())) gameBoard[0][0] = new Piece(1,6);
        //if(checkStatus) checkMate(p.getPlayerID());//double check later if this works
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

    private boolean checkReverseCheck(int row, int col, int pRow, int pCol){
        Piece p = getTile(pRow,pCol);
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

        gameBoard[row][col] = gameBoard[pRow][pCol];
        gameBoard[pRow][pCol] = null;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameBoard[i][j] != null && gameBoard[i][j].getPlayerID() == opponentID){
                    if(gameBoard[i][j].getPieceID() == 6){
                        if(gameBoard[i][j].isValidMoveModified(kingCoordinates.x, kingCoordinates.y, i,j,this)) {
                            for(int k = 0; k < 8; k++) {
                                System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                            }
                            //checkStatus = true;
                            return false;
                        }
                    }
                    else if(gameBoard[i][j].isValidMove(kingCoordinates.x, kingCoordinates.y,i,j,this)) {
                        for(int k = 0; k < 8; k++) {
                            System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                        }
                        //checkStatus = true;
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

    private boolean checkReverseCheckModified(int row, int col, int pRow, int pCol){
        Piece p = getTile(pRow,pCol);
        Piece[][] holdBoard = new Piece[8][8];
        Point kingCoordinates = new Point(row,col);
        int opponentID;

        if(p.getPlayerID() == 2) opponentID = 1;
        else opponentID = 2;

        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                holdBoard[i][j] = gameBoard[i][j];
            }
        }

        gameBoard[row][col] = gameBoard[pRow][pCol];
        gameBoard[pRow][pCol] = null;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameBoard[i][j] != null && gameBoard[i][j].getPlayerID() == opponentID){
                    if(gameBoard[i][j].getPieceID() == 6){
                        if(gameBoard[i][j].isValidMoveModified(kingCoordinates.x, kingCoordinates.y, i,j,this)) {
                            for(int k = 0; k < 8; k++) {
                                System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                            }
                            //checkStatus = true;
                            return false;
                        }
                    }
                    else if(gameBoard[i][j].isValidMove(kingCoordinates.x, kingCoordinates.y,i,j,this)) {
                        for(int k = 0; k < 8; k++) {
                            System.arraycopy(holdBoard[k], 0, gameBoard[k], 0, 8);
                        }
                        //checkStatus = true;
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

    public void castlingCleanUp(int row, int col){

        if(col > 4){
            if(!checkReverseCheckModified(row,6,selectedTile.x,selectedTile.y)) return;

            gameBoard[row][6] = gameBoard[row][4];
            gameBoard[row][5] = gameBoard[row][7];
            gameBoard[row][7] = null;
            gameBoard[row][4] = null;

            gameBoard[row][6].setHasMoved();
            gameBoard[row][5].setHasMoved();

            selectedTile.setLocation(1000,1000);
        }
        else{
            if(!checkReverseCheckModified(row,2,selectedTile.x,selectedTile.y)) return;

            gameBoard[row][2] = gameBoard[row][4];
            gameBoard[row][3] = gameBoard[row][0];
            gameBoard[row][0] = null;
            gameBoard[row][4] = null;

            gameBoard[row][2].setHasMoved();
            gameBoard[row][3].setHasMoved();

            selectedTile.setLocation(1000,1000);
        }
    }

    private boolean checkMate(int playerID){//make sure check reverse check doesnt send true for tiles with friendlies
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(gameBoard[i][j] == null || gameBoard[i][j].getPlayerID() == playerID) continue;

                if(gameBoard[i][j].getPieceID() == 1){
                    if(gameBoard[i][j].getPlayerID() == 1){
                        if(gameBoard[i][j].isValidMove(i+1,j-1,i,j,this) && checkReverseCheck(i+1,j-1,i,j)) return false;
                        if(gameBoard[i][j].isValidMove(i+1,j+1,i,j,this) && checkReverseCheck(i+1,j+1,i,j)) return false;
                        if(checkReverseCheck(i+1,j,i,j)) return false;
                        if(!gameBoard[i][j].getHasMoved() && checkReverseCheck(i+2,j,i,j)) return false;
                    }
                    else{
                        if(gameBoard[i][j].isValidMove(i-1,j-1,i,j,this) && checkReverseCheck(i-1,j-1,i,j)) return false;
                        if(gameBoard[i][j].isValidMove(i-1,j+1,i,j,this) && checkReverseCheck(i-1,j+1,i,j)) return false;
                        if(checkReverseCheck(i-1,j,i,j)) return false;
                        if(!gameBoard[i][j].getHasMoved() && checkReverseCheck(i-2,j,i,j)) return false;
                    }
                }
                else if(gameBoard[i][j].getPieceID() == 2){
                    int row1 = i-2, row2 = i-1, row3 = i+1, row4 = i+2, col1 = j-2, col2 = j-1, col3 = j+1, col4 = j+2;

                    if(row1 >= 0 && col2 >= 0 && checkReverseCheck(row1,col2,i,j)) return false;
                    if(row1 >= 0 && col3 <= 7 && checkReverseCheck(row1,col3,i,j)) return false;
                    if(row2 >= 0 && col1 >= 0 && checkReverseCheck(row2,col1,i,j)) return false;
                    if(row2 >= 0 && col4 <= 7 && checkReverseCheck(row2,col4,i,j)) return false;
                    if(row3 <= 7 && col1 >= 0 && checkReverseCheck(row3,col1,i,j)) return false;
                    if(row3 <= 7 && col4 <= 7 && checkReverseCheck(row3,col4,i,j)) return false;
                    if(row4 <= 7 && col2 >= 0 && checkReverseCheck(row4,col2,i,j)) return false;
                    if(row4 <= 7 && col3 <= 7 && checkReverseCheck(row4,col3,i,j)) return false;
                }
                else if(gameBoard[i][j].getPieceID() == 3){
                    int row1 = i-1, row2 = i+1, col1 = j-1, col2 = j+1;

                    if(row1 >= 0 && col1 >= 0 && checkReverseCheck(row1,col1,i,j)) return false;
                    if(row1 >= 0 && col2 <= 7 && checkReverseCheck(row1,col2,i,j)) return false;
                    if(row2 <= 7 && col1 >= 0 && checkReverseCheck(row2,col1,i,j)) return false;
                    if(row2 <= 7 && col2 <= 7 && checkReverseCheck(row2,col2,i,j)) return false;
                }
                else if(gameBoard[i][j].getPieceID() == 4){
                    int row1 = i-1, row2 = i+1, col1 = j-1, col2 = j+1;

                    if(row1 >= 0 && checkReverseCheck(row1,j,i,j)) return false;
                    if(col1 >= 0 && checkReverseCheck(i,col1,i,j)) return false;
                    if(col2 <= 7 && checkReverseCheck(i,col2,i,j)) return false;
                    if(row2 <= 7 && checkReverseCheck(row2,j,i,j)) return false;
                }
                else if(gameBoard[i][j].getPieceID() == 5){
                    int row1 = i-1, row2 = i+1, col1 = j-1, col2 = j+1;

                    if(row1 >= 0 && col1 >= 0 && checkReverseCheck(row1,col1,i,j)) return false;
                    if(row1 >= 0 && checkReverseCheck(row1,j,i,j)) return false;
                    if(row1 >= 0 && col2 <= 7 && checkReverseCheck(row1,col2,i,j)) return false;
                    if(col1 >= 0 && checkReverseCheck(i,col1,i,j)) return false;
                    if(col2 <= 7 && checkReverseCheck(i,col2,i,j)) return false;
                    if(row2 <= 7 && col1 >= 0 && checkReverseCheck(row2,col1,i,j)) return false;
                    if(row2 <= 7 && checkReverseCheck(row2,j,i,j)) return false;
                    if(row2 <= 7 && col2 <= 7 && checkReverseCheck(row2,col2,i,j)) return false;
                }
                else if(gameBoard[i][j].getPieceID() == 6){
                    int row1 = i-1, row2 = i+1, col1 = j-1, col2 = j+1;

                    if(row1 >= 0 && col1 >= 0 && gameBoard[i][j].isValidMove(row1,col1,i,j,this) && checkReverseCheckModified(row1,col1,i,j)) return false;
                    if(row1 >= 0 && gameBoard[i][j].isValidMove(row1,j,i,j,this) && checkReverseCheckModified(row1,j,i,j)) return false;
                    if(row1 >= 0 && gameBoard[i][j].isValidMove(row1,col2,i,j,this) && col2 <= 7 && checkReverseCheckModified(row1,col2,i,j)) return false;
                    if(col1 >= 0 && gameBoard[i][j].isValidMove(i,col1,i,j,this) && checkReverseCheckModified(i,col1,i,j)) return false;
                    if(col2 <= 7 && gameBoard[i][j].isValidMove(i,col2,i,j,this) && checkReverseCheckModified(i,col2,i,j)) return false;
                    if(row2 <= 7 && gameBoard[i][j].isValidMove(row2,col1,i,j,this) && col1 >= 0 && checkReverseCheckModified(row2,col1,i,j)) return false;
                    if(row2 <= 7 && gameBoard[i][j].isValidMove(row2,j,i,j,this) && checkReverseCheckModified(row2,j,i,j)) return false;
                    if(row2 <= 7 && gameBoard[i][j].isValidMove(row2,col2,i,j,this) && col2 <= 7 && checkReverseCheckModified(row2,col2,i,j)) return false;
                }
            }
        }

        return true;
    }
}
