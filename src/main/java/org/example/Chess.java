package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Chess {
    private final static int SIZE = 8;
    private final static String[][] board = new String[SIZE][SIZE];

    public static void startboard(){
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = " ";
            }
        }
        for (int i = 2; i < 7; i++){
            for (int j = 0; j < SIZE; j++) {
                board[j][i]  ="* ";
            }
        }
        for(int i = 0; i < SIZE; i++){
            board[i][1] = "bP";
            board[i][6] = "wP";
        }
        board[0][0] = "bR";
        board[7][0] = "bR";
        board[1][0] = "bN";
        board[6][0] = "bN";
        board[2][0] = "bB";
        board[5][0] = "bB";
        board[3][0] = "bQ";
        board[4][0] = "bK";

        board[7][7] = "wR";
        board[0][7] = "wR";
        board[1][7] = "wN";
        board[6][7] = "wN";
        board[2][7] = "wB";
        board[5][7] = "wB";
        board[3][7] = "wQ";
        board[4][7] = "wK";

        print(board);
    }

    public static void print(String[][] board) {
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            System.out.print(row+1 + "  ");
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[column][row] + " ");
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public static void main(String[] args) {
        Player p = new Player();
        startboard();
    }
}