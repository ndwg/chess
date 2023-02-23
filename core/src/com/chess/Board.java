package com.chess;

public class Board {
    private int[][] gameBoard;
    public Board(){
        setBoard();
    }

    public void setBoard(){
        gameBoard = new int[8][8];

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

    //public void updateBoard(){}
}
