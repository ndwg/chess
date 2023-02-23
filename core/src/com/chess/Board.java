package com.chess;

public class Board {
    private int[][] gameboard;

    public Board(){
        setBoard();
    }

    public void setBoard(){
        gameboard = new int[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0){
                    if(j == 0 || j == 7) gameboard[i][j] = 14;
                    else if(j == 1 || j == 6) gameboard[i][j] = 12;
                    else if(j == 2 || j == 5) gameboard[i][j] = 13;
                    else if(j == 3) gameboard[i][j] = 15;
                    else if(j == 4) gameboard[i][j] = 16;
                }
                else if(i == 1) gameboard[i][j] = 11;
                else if(i == 6) gameboard[i][j] = 21;
                else if(i == 7){
                    if(j == 0 || j == 7) gameboard[i][j] = 24;
                    else if(j == 1 || j == 6) gameboard[i][j] = 22;
                    else if(j == 2 || j == 5) gameboard[i][j] = 23;
                    else if(j == 3) gameboard[i][j] = 25;
                    else if(j == 4) gameboard[i][j] = 26;
                }
                else gameboard[i][j] = 0;
            }
        }
    }

    //public int[][] getBoard(){}

    //public void updateBoard(){}
}
