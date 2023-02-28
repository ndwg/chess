package com.chess;

import java.awt.*;

public class Board {
    private int[][] gameBoard;
    private Point selectedTile;
    public Board(){
        setBoard();
    }

    public void setBoard(){
        gameBoard = new int[8][8];
        selectedTile = new Point(1000,1000);

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    if(j == 0 || j == 7) gameBoard[i][j] = 14;
                    else if(j == 1 || j == 6) gameBoard[i][j] = 12;
                    else if(j == 2 || j == 5) gameBoard[i][j] = 13;
                    else if(j == 3) gameBoard[i][j] = 15;
                    else gameBoard[i][j] = 16;
                }
                else if(i == 1) gameBoard[i][j] = 11;
                else if(i == 6) gameBoard[i][j] = 21;
                else if(i == 7){
                    if(j == 0 || j == 7) gameBoard[i][j] = 24;
                    else if(j == 1 || j == 6) gameBoard[i][j] = 22;
                    else if(j == 2 || j == 5) gameBoard[i][j] = 23;
                    else if(j == 3) gameBoard[i][j] = 25;
                    else gameBoard[i][j] = 26;
                }
                else gameBoard[i][j] = 0;
            }
        }
    }

    public int getTile(int row, int col){
        return gameBoard[row][col];
    }

    public void selectTile(int row, int col){
        if(row == selectedTile.x && col == selectedTile.y) selectedTile.setLocation(1000,1000);
        else if(selectedTile.x != 1000 && gameBoard[selectedTile.x][selectedTile.y] != 0) updateBoard(row,col);
        else selectedTile.setLocation(row,col);
    }

    public Point getSelectedTile(){
        return selectedTile;
    }

    private void updateBoard(int row, int col){
        boolean updateApproved = false;
        int tileID;

        if(gameBoard[selectedTile.x][selectedTile.y] > 20) tileID = gameBoard[selectedTile.x][selectedTile.y]%20;
        else tileID = gameBoard[selectedTile.x][selectedTile.y]%10;

        switch(tileID) {
            case 1:
                updateApproved = isValidMovePawn(row,col);
                break;
            case 2:
                updateApproved = isValidMoveKnight(row,col);
                break;
            case 3:
                updateApproved = isValidMoveBishop(row,col);
                break;
        }

        if(updateApproved){
            gameBoard[row][col] = gameBoard[selectedTile.x][selectedTile.y];
            gameBoard[selectedTile.x][selectedTile.y] = 0;
            selectedTile.setLocation(1000,1000);
        }
        else selectedTile.setLocation(row,col);
    }

    private boolean isValidMovePawn(int row, int col){
        if(gameBoard[selectedTile.x][selectedTile.y] > 20){
            if(row == selectedTile.x-1 && col == selectedTile.y && gameBoard[row][col] == 0) return true;
            else if((col == selectedTile.y+1 || col == selectedTile.y-1) && row == selectedTile.x-1 && 0 < gameBoard[row][col] && gameBoard[row][col] < 20) return true;
        }
        else{
            if(row == selectedTile.x+1 && col == selectedTile.y && gameBoard[row][col] == 0) return true;
            else if((col == selectedTile.y+1 || col == selectedTile.y-1) && row == selectedTile.x+1 && gameBoard[row][col] > 20) return true;
        }

        return false;
    }

    private boolean isValidMoveKnight(int row, int col){
        if(gameBoard[selectedTile.x][selectedTile.y] > 20){
            if(((row == selectedTile.x-2 && Math.abs(col-selectedTile.y) == 1)
                    || (row == selectedTile.x-1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+2 && Math.abs(col-selectedTile.y) == 1))
                    && gameBoard[row][col] < 20)
                return true;
        }
        else{
            if(((row == selectedTile.x-2 && Math.abs(col-selectedTile.y) == 1)
                    || (row == selectedTile.x-1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+1 && Math.abs(col-selectedTile.y) == 2)
                    || (row == selectedTile.x+2 && Math.abs(col-selectedTile.y) == 1))
                    && (gameBoard[row][col] == 0 || gameBoard[row][col] > 20))
                return true;
        }
        return false;
    }

    private boolean isValidMoveBishop(int row, int col){
        if(gameBoard[selectedTile.x][selectedTile.y] > 20){
            if(Math.abs(row-selectedTile.x)==Math.abs(col-selectedTile.y)
                    && gameBoard[row][col] < 20) {
                if(row < selectedTile.x) row++;
                else row--;

                if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != 0) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                    if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        else{
            if(Math.abs(row-selectedTile.x)==Math.abs(col-selectedTile.y)
                    && (gameBoard[row][col] > 20 || gameBoard[row][col] == 0)) {
                if(row < selectedTile.x) row++;
                else row--;

                if(col < selectedTile.y) col++;
                else col--;

                while(row != selectedTile.x){
                    if(gameBoard[row][col] != 0) return false;

                    if(row < selectedTile.x) row++;
                    else row--;

                    if(col < selectedTile.y) col++;
                    else col--;
                }
                return true;
            }
        }
        return false;
    }
}
