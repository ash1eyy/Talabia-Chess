import java.util.ArrayList;

/* 
 * The PointPiece is a type of piece, thus it is a subclass of the Piece class.
 * It is equivalent to the Pawn in normal chess, but can only capture other pieces in front of it rather than diagonally.
 * 
 * Design Pattern:
 * 1. MVC - Part of the Model
 */

public class PointPiece extends Piece {
    PointPiece() {
        super();
    }

    /* 
    
    Point Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)

    */

    PointPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }

    @Override


    /* 
    getValidMoves()
    Done by:
    Ashley Sim Ci Hui

    Gets a 2D ArrayList of all the valid moves for the given piece
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    @return validMoves 2D ArrayList with all the valid moves
    */
   
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        for (int i = 1; i < 3; i++) {
            int newX = this.getX();
            int newY = this.getY();

            if (this.getDir().equals("up"))
                newY -= i;
            else if (this.getDir().equals("down"))
                newY += i;

            while (newY >= 0 &&
                newY < board.getY() &&
                getColour().equals(player)) {

                if (board.isEmpty(newX, newY))  // doesnt let the point piece to skip over another piece
                    addValidMove(validMoves, newX, newY);
                else if (!board.isEmpty(newX, newY) &&
                        !board.getPiece(newX, newY).getColour().equals(this.getColour())) {

                    addValidMove(validMoves, newX, newY);
                    return validMoves;
                }
                else
                    return validMoves;
                break;
            }
        }
        return validMoves;
    }

    /* 
    Get piece name method returns piece name
    Point piece has direction since it will turn around
    @return "point" + this.getDir() piece name and its direction
    
    To string method, returns following piece onto the board
    @return O symbol of piece  
    */

    @Override
    public String getPieceName() {
        return "Point" + this.getDir(); 
    }
}