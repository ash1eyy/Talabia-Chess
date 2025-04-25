// import javax.swing.*;

/* 
 * Main function for the whole game.
 * Creates the view and controller classes, thus starting the game.
 */

public class Game { 
    public static void main(String[] args) {
        GameController controller = new GameController();

        GameView view = new GameView(controller);
        controller.setGameView(view);
    }
}