package com.chess;

import java.awt.*;

public class Board {
    private Piece[][] gameBoard;
    private Point selectedTile;
    public Board(){
        setBoard();
    }

    public void setBoard(){
        gameBoard = new Piece[8][8];
        selectedTile = new Point(1000,1000);

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

    private void updateBoard(int row, int col){
        boolean updateApproved;
        Piece p = getTile(selectedTile.x,selectedTile.y);

        updateApproved = p.isValidMove(row,col,p,selectedTile,gameBoard);

        if(updateApproved){
            gameBoard[row][col] = gameBoard[selectedTile.x][selectedTile.y];
            gameBoard[selectedTile.x][selectedTile.y] = null;
            selectedTile.setLocation(1000,1000);
        }
        else selectedTile.setLocation(row,col);
    }
}
