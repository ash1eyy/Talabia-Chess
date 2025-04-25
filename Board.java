import java.util.ArrayList;
import java.util.Collections;

/* 
The Board class has a 2D ArrayList that stores all of the Pieces' positions in the board.
It has methods to get/set the piece at specified coordinates, as well as checking whether that spot on the board is empty or not.
There are also methods to flip the board as well as switch the time pieces to plus pieces and vice versa.

Design Pattern:
1. MVC - Part of the Model
2. Singleton
*/


public class Board { 
    /* Data Field */
    private static Board singleInstance = null;
    private final int dimX = 7;
    private final int dimY = 6;
    private ArrayList<ArrayList<Piece>> map;

    private Board() {}

    /* 
    Getters and Setters methods to get and set the private varaiables
    */

    public int getX() {
        return dimX;
    }

    public int getY() {
        return dimY;
    }

    public ArrayList<ArrayList<Piece>> getMap() { /* 2d array of the board */
        return map;
    }

    /* 
    init()
    Done by:
    Ashley Sim Ci Hui

    Initializes the pieces on the board. 
    */
    public void init() {
        map = new ArrayList<ArrayList<Piece>>(dimY);
        
        for (int i = 0; i < dimY; i++) {
            map.add(new ArrayList<Piece>());
        }

        //adds pieces into row 0
        map.get(0).add(new PlusPiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new SunPiece());
        map.get(0).add(new TimePiece());
        map.get(0).add(new HourglassPiece());
        map.get(0).add(new PlusPiece());

        //adds all the pieces for rows 1-4
        for (int i = 0; i < dimX; i++) {
            map.get(1).add(new PointPiece());
            map.get(2).add(null);
            map.get(3).add(null);
            map.get(dimY - 2).add(new PointPiece());
        }

        //initializes variables for each piece in row 0 & 1
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < dimX; j++) {
                map.get(i).get(j).setX(j);
                map.get(i).get(j).setY(i);
                map.get(i).get(j).setColour("blue");
                map.get(i).get(j).setDir("down");
            }
        }

        //adds all the pieces for row 6
        map.get(dimY - 1).add(new PlusPiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new SunPiece());
        map.get(dimY - 1).add(new TimePiece());
        map.get(dimY - 1).add(new HourglassPiece());
        map.get(dimY - 1).add(new PlusPiece());

        //initializes variables for each piece in row 5 & 6
        for (int i = dimY - 2; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                map.get(i).get(j).setX(j);
                map.get(i).get(j).setY(i);
                map.get(i).get(j).setColour("yellow");
                map.get(i).get(j).setDir("up");
            }
        }
    }

    /* 
    createInstance()
    Done by:
    Ashley Sim Ci Hui

    Checks to see if an instance of the board already exists
    If yes, returns that instance, if no, create a new instance 

    @return singleInstance
    */

    public static synchronized Board createInstance() { 
        if (singleInstance == null) 
            singleInstance = new Board();
        
        return singleInstance;
    }

    /* 
    flipBoard()
    Done by:
    Thasharn Surein A/L Vinod Anand

    This method will flip the board 
    It reiterates through the array with a loop 
    The method will check if the object at the coordinates are null 
    If not null the objects will be set with i and j coordinate values 
    */

    public void flipBoard() {
        Collections.reverse(map);

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) {
                    objectAtCoords.setX(j);
                    objectAtCoords.setY(i);

                    if (objectAtCoords.getDir() != null)
                        objectAtCoords.flipDir();
                }
            }
        }
    }

    /* 
    getPiece()
    Done by:
    Shashikaran A/L Kalairasu
    
    Method that gets piece
      
    @param x X coordinate of piece
    @param y Y coordinate of piece
    @return map.get(y).get(x) the position of that piece on the board
    */

    public Piece getPiece(int x, int y) {
		return map.get(y).get(x);
    }

    /*
    setPiece()
    Done by:
    Shashikaran A/L Kalairasu
    
    Method that sets piece

    @param x X coordinate of piece
    @param y Y coordinate of piece
    @param piece Piece on the board
    */

    public void setPiece(int x, int y, Piece piece) {
        map.get(y).set(x, piece);
    }

    /*
    isEmpty()
    Done by:
    Shashikaran A/L Kalairasu

    Method if there is no piece at the coordinate

    @param x X Coordinate
    @param y Y Coordinate
    @return true or false
    */

    public boolean isEmpty(int x, int y) { 
		if (map.get(y).get(x) == null)
            return true;
        else
            return false;
    }

    /* 
    switchTimeAndPlus()
    Done by:
    Ashley Sim Ci Hui

    Method that switches time and plus 
    */


    public void switchTimeAndPlus() { 
        String pieceColour = ""; 

        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {

                /* 
                Gets the coordinates of the following piece 
                If not null switch case occurs 
                sets the time piece into a plus piece
                sets colour of piece from the colour it was before
                sets the plus piece into a time piece
                */

                Piece objectAtCoords = map.get(i).get(j);

                if (objectAtCoords != null) { 
                    switch (objectAtCoords.getPieceName()) { 
                        case "Time":
                            pieceColour = objectAtCoords.getColour();
                            setPiece(j, i, new PlusPiece()); 
                            getPiece(j, i).setX(j);
                            getPiece(j, i).setY(i);
                            map.get(i).get(j).setColour(pieceColour); 
                            break;

                        case "Plus":
                            pieceColour = objectAtCoords.getColour();
                            setPiece(j, i, new TimePiece()); 
                            getPiece(j, i).setX(j);
                            getPiece(j, i).setY(i);
                            map.get(i).get(j).setColour(pieceColour);
                            break;

                        default:
                            break;
                    }
                }
            }
        }
    }
}