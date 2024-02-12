package org.example;

abstract class Piece {
    private Position currentPosition;
    private final Player player;
    public Piece(Player player){
        this.player = player;
    }
    /**
     * This method does not have to check the validity of the position
     */
    public void setArbitraryPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    public void setArbitraryPosition(char x, int y) {
        this.currentPosition = new Position(x,y);
    }
    Position getPosition(){
        return this.currentPosition;
    }
    /**
     * This method checks if the position is a valid position
     */
    public abstract boolean isValidPosition(Position newPosition);
}



class Player{
    private boolean white;
    public Player(String string){

    }
    public void setColorWhite(boolean b) {
        this.white = b;
    }
    public boolean getColor(){
        return white;
    }
}



class Position{
    private final char x;
    private final int y;
    public Position(char x, int y){
        this.x = x;
        this.y = y;
    }
    public char getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}



class Rook extends Piece{
    public Rook(Player player){
        super(player);

    }
    @Override
    public boolean isValidPosition(Position newPosition) {
        Position currentPosition = this.getPosition();

        int XDiff = Math.abs(newPosition.getX() - currentPosition.getX());
        int YDiff = Math.abs(newPosition.getY() - currentPosition.getY());

        return (XDiff == 0 && YDiff != 0) || (XDiff != 0 && YDiff == 0);
    }
}

class Knight extends Piece{
    public Knight(Player player){
        super(player);
    }
    @Override
    public boolean isValidPosition(Position newPosition) {
        Position currentPosition = this.getPosition();

        int XDiff = Math.abs(newPosition.getX() - currentPosition.getX());
        int YDiff = Math.abs(newPosition.getY() - currentPosition.getY());

        return (XDiff == 2 && YDiff == 1) || (XDiff == 1 && YDiff == 2);
    }
}
class Pawn extends Piece{
    public Pawn(Player player){
        super(player);
    }
    boolean moved = false;
    @Override
    public boolean isValidPosition(Position newPosition) {
        Position currentPosition = this.getPosition();

        int XDiff = Math.abs(newPosition.getX() - currentPosition.getX());
        int YDiff = Math.abs(newPosition.getY() - currentPosition.getY());

        if(!moved) {
            return (XDiff == 0 && YDiff <= 2 && YDiff > 0);
        } else {
            return (XDiff == 0 && YDiff == 1);
        }
    }
}
class King extends Piece{
    public King(Player player){
        super(player);
    }
    @Override
    public boolean isValidPosition(Position newPosition) {
        Position currentPosition = getPosition();

        int XDiff = Math.abs(newPosition.getX() - currentPosition.getX());
        int YDiff = Math.abs(newPosition.getY() - currentPosition.getY());

        return (XDiff <= 1 && YDiff <= 1) && (XDiff + YDiff != 0);
    }
}


class Bishop extends Piece{
    public Bishop(Player player){
        super(player);
    }
    @Override
    public boolean isValidPosition(Position newPosition) {
        Position currentPosition = getPosition();

        int XDiff = Math.abs(newPosition.getX() - currentPosition.getX());
        int YDiff = Math.abs(newPosition.getY() - currentPosition.getY());

        return (XDiff - YDiff == 0) && (XDiff + YDiff != 0);
    }
}

public class Chess {
    public static void main(String[] args) {
        Player p1 = new Player("White player");
        p1.setColorWhite(true);
        Player p2 = new Player("Black player");
        p2.setColorWhite(false);

        System.out.println("Testing kings:");
        Piece whiteKing = new King(p1);
        whiteKing.setArbitraryPosition(new Position('f', 5));
        test(whiteKing, 'a', 1, false);
        test(whiteKing, 'f', 4, true);

        System.out.println("Testing rooks:");
        Rook blackRook = new Rook(p2);
        blackRook.setArbitraryPosition('d', 5);
        test(blackRook, 'h', 5, true);
        test(blackRook, 'h', 1, false);
        test(blackRook, 'd', 9, false);

        System.out.println("Testing bishops:");
        Piece whiteBishop = new Bishop(p1);
        whiteBishop.setArbitraryPosition(new Position('d', 5));
        test(whiteBishop, 'b', 2, false);
        test(whiteBishop, 'a', 8, true);

        System.out.println("Testing knigts:");
        Knight blackKnight = new Knight(p2);
        blackKnight.setArbitraryPosition('d', 4);
        test(blackKnight, 'e', 6, true);
        test(blackKnight, 'f', 6, false);
        test(blackKnight, 'c', 2, true);
        test(blackKnight, 'i', 8, false);

        System.out.println("Testing pawns:");
        Pawn whitePawn = new Pawn(p1);
        Pawn blackPawn = new Pawn(p2);
        blackPawn.setArbitraryPosition('b', 4);
        test(blackPawn, 'b', 3, true);
        test(blackPawn, 'b', 5, false);
        whitePawn.setArbitraryPosition('f', 2);
        test(whitePawn, 'f', 3, true);
        test(whitePawn, 'f', 4, true);
        blackPawn.setArbitraryPosition('g', 5);
        test(blackPawn, 'g', 4, true);
        test(blackPawn, 'g', 3, false);
        whitePawn.setArbitraryPosition('e', 7);
        test(whitePawn, 'd', 8, false);
        test(whitePawn, 'f', 8, false);
    }

    public static void test(Piece p, char x, int y, boolean valid) {
        if (p.isValidPosition(new Position(x, y)) == valid) {
            System.out.println("  > Test passed!");
        } else {
            System.out.println("  X Test NOT passed!");
        }
    }
}