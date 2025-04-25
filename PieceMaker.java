import java.util.HashMap;

/* 
 * The PieceMaker class creates a Piece object based on the name of its class (i.e PlusPiece, PointPiece etc).
 * 
 * Design Pattern:
 * 1. MVC - Part of the Model
 */

public class PieceMaker {
    private static HashMap<String, Piece> pieces = new HashMap<String, Piece>();

    /* 
     * toPiece()
     * Done by:
     * Ashley Sim Ci Hui
     * 
     * Creates a given piece object based on its class name
     * @param pieceName Name of the piece's class
     * @return piece Newly created piece object
     */
    public static Piece toPiece(String pieceName) {
        pieces.put("PlusPiece", new PlusPiece());
        pieces.put("HourglassPiece", new HourglassPiece());
        pieces.put("TimePiece", new TimePiece());
        pieces.put("SunPiece", new SunPiece());
        pieces.put("PointPiece", new PointPiece());

        Piece piece = pieces.get(pieceName);
        return piece;
    }
}
