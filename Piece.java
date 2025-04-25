import java.util.*;

/* 
 * The Piece class is an abstract superclass.
 * It defines the shared attributes that every piece needs, such as its X and Y coordinates, colour, and direction.
 * It also defines abstract functions like getPieceName() and getValidMoves() that differ for each piece type.
 * It also has a method that can flip the direction of a piece from up to down.
 * 
 * Design Pattern:
 * 1. MVC - Part of the Model
 */

public abstract class Piece {
    private int pieceX;
    private int pieceY;
    private String pieceColour;
    private String direction;

    /* Constructor*/
    Piece() {}

    /* 
    Point Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)
    */

    Piece(int pieceX, int pieceY, String pieceColour, String direction) {  //zafran did this
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.pieceColour = pieceColour;
        this.direction = direction;
    }
    
    /* 
    Getters and Setters methods to get and set the private varaiables
    */

    public int getX() { 
        return pieceX;
    }

    public int getY() {
        return pieceY;
    }

    public String getColour() { 
        return pieceColour;
    }

    public String getDir() { 
        return direction;
    }

    public void setX(int pieceX) {
        this.pieceX = pieceX;
    }

    public void setY(int pieceY) {
        this.pieceY = pieceY;
    }

    public void setColour(String colour) {
        this.pieceColour = colour;
    }

    public void setDir(String direction) {
        this.direction = direction;
    }

    /* 
    Gets the name of the piece (Point, Sun, Hourglass, etc)
    */
    
    public abstract String getPieceName();

    /* 
    getValidMoves()
    Gets a 2D ArrayList of all the valid moves for the given piece
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    @return validMoves 2D ArrayList with all the valid moves
    */
    public abstract ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player);

    /* 
    addValidMove()
    Done by:
    Javier Austin Anak Jawa

    The add valid moves method has the following parameters

    @param validMoves 2d ArrayLists of Integers
    @param validX valid X coordinates of a piece
    @param validY valid Y coordinates of a piece
    */
    public void addValidMove(ArrayList<ArrayList<Integer>> validMoves, int validX, int validY) {
        validMoves.add(new ArrayList<Integer>());
        validMoves.get(validMoves.size() - 1).add(validX);
        validMoves.get(validMoves.size() - 1).add(validY);
    }

    /* 
     * flipDir()
     * Done by:
     * Thasharn Surein A/L Vinod Anand
     * 
     * Flips the direction of the piece (up/down)
     */

    public void flipDir() {
        switch (direction) {
            case "up":
                setDir("down");
                break;
            case "down":
                setDir("up");
                break;
        }
    }
}