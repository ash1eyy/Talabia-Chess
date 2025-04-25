import javax.swing.*;
import java.awt.*;

/* 
 * The PieceButton class is a subclass of the JButton class.
 * It introduces a few more attributes that need to be kept track of as the game is running.
 * 
 * Design Pattern:
 * 1. MVC - Part of the View
 */

public class PieceButton extends JButton {
    private Piece piece = null;
    private int pieceX, pieceY;
    private boolean selected = false;
    private Color selectedColour = new Color(255,255,153);

    PieceButton() {}

    /*    
    Getters and Setters methods to get and set the private varaiables
    */

    public Piece getPiece() {
        return piece;
    }

    public int getPieceX() {
        return pieceX;
    }

    public int getPieceY() {
        return pieceY;
    }

    public boolean getSelected() {
        return selected;
    }

    /* 
    @param piece Pieces on the board
    @param x coordinates
    @param y coordinates
    */

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setX(int pieceX) {
        this.pieceX = pieceX;
    }

    public void setY(int pieceY) {
        this.pieceY = pieceY;
    }

    /* 
    setSelected()
    Done by:
    Muhammad Zafran Bin Mohd Anuar

    Sets the corresponding piece as selected
    Only selected if the button has a piece (not null) and the piece's colour is the same as the player's
    OR if the button is null, it selects the button but does not change its background colour

    @param player The current player's colour (blue/yellow)
    */
    public void setSelected(String player) { 
        if (piece != null && piece.getColour().equals(player)) {
            this.selected = true;
            this.setBackground(selectedColour);
        }
        else if (piece == null) {
            this.selected = true;
        }
    }
}
