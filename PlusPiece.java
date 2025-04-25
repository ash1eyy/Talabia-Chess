import java.util.ArrayList;

/* 
 * The PlusPiece is a type of piece, thus it is a subclass of the Piece class.
 * It is equivalent to the Rook in normal chess.
 * 
 * Design Pattern:
 * 1. MVC - Part of the Model
 */

public class PlusPiece extends Piece {
    PlusPiece() {
        super(); //Inheritance
    }

    /* 
    
    Plus Piece constructor that has the parameters

    @param pieceX Piece's X coordinate
    @param pieceY Piece's Y coordinate
    @param pieceColour Colour of piece (blue or yellow)
    @param direction Direction of piece (up/down)

    */

    PlusPiece(int pieceX, int pieceY, String pieceColour, String direction) {
        super(pieceX, pieceY, pieceColour, direction);
    }


    /* 
    getValidMoves()
    Done by:
    Muhammad Zafran Bin Mohd Anuar

    Gets a 2D ArrayList of all the valid moves for the given piece
    
    @param board Game board with all the pieces
    @param player The current player's colour (blue/yellow)
    @return validMoves 2D ArrayList with all the valid moves
    */
    @Override
    public ArrayList<ArrayList<Integer>> getValidMoves(Board board, String player) {
        ArrayList<ArrayList<Integer>> validMoves = new ArrayList<ArrayList<Integer>>();

        int checkX, checkY;
        int[][] nextCoords = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] next : nextCoords) {
            checkX = this.getX() + next[0];
            checkY = this.getY() + next[1];

            while (checkX < board.getX() &&
                checkY < board.getY() &&
                checkX >= 0 && checkY >= 0 &&
                getColour().equals(player)) {
                
                //if given space is empty, continue
                if (board.isEmpty(checkX, checkY)) {
                    addValidMove(validMoves, checkX, checkY);

                    checkX += next[0];
                    checkY += next[1];
                    continue;
                }
                else {
                    //if current piece and piece at space are diff colours, continue
                    if (!this.getColour().equals(board.getPiece(checkX, checkY).getColour())) {
                        addValidMove(validMoves, checkX, checkY);
                        break;
                    }
                }
                break;
            }
        }
        return validMoves;
    }

    /* 
    Get piece name method returns piece name
    @return "plus" piece name
    
    To string method, returns following piece onto the board
    @return + symbol of piece
    */

    @Override
    public String getPieceName() {
        return "Plus";
    }
}