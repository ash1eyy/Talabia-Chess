import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.*;


/* 
The GameView class is the main window that the game's GUI runs in.
The "board" is made up of buttons, with the button icons representing the Piece at that location.

Design Pattern:
1. MVC - Part of the View
*/


public class GameView extends JFrame {
    /* 
    originalPiece piece that was clicked before (if it exist) 
    newPiece piece that was just selected
    boardPanel turned into a variable for easy access
    buttons 2d Array
    */

    private final int dimX = 7;
    private final int dimY = 6;
    private PieceButton originalPiece = null; 
    private PieceButton newPiece = null; 
    private boolean pieceSelected = false;
    private GameController controller;
    private JPanel boardPanel = new JPanel(new GridLayout(dimY, dimX)); /* Turned into a variable for easy access */
    private PieceButton[][] buttons = new PieceButton[dimY][dimX]; /* So that every button has an x and y coordinate */
    private static JLabel displayPlayerTurn;

    /* 
     * GameView()
     * Done by:
     * Javier Austin Anak Jawa
     * Ashley Sim Ci Hui
     * 
     * Constructor for the GameView
     * Initializes all the JPanels and JButtons in the JFrame
     * @param controller Game controller
     */

    public GameView(GameController controller) {
        super("Talabia Chess");
        this.controller = controller;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(950, 600)); //prefered size
        setMinimumSize(new Dimension(950, 600));

        JPanel boardContainer = new JPanel(new GridBagLayout());
        boardContainer.add(boardPanel);
        boardContainer.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeBoard(boardPanel, boardContainer);
            }
        });

        /* Adds the buttons to the panel*/
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                PieceButton button = new PieceButton() {
                    {
                        setPreferredSize(new Dimension(50, 50));
                        setOpaque(true);
                        setBorderPainted(true);
                        addActionListener(new PieceListener());
                        setEnabled(false); /* All piece buttons disabled before game starts */
                    }
                };
                button.setX(j);
                button.setY(i); 
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        add(boardContainer, BorderLayout.CENTER);

        JPanel buttonPanel = buttonPanel();
        add(buttonPanel, BorderLayout.WEST);

        JPanel infoPanel = infoPanel(controller);
        add(infoPanel, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* Getters and Setters method for GameView*/

    public PieceButton getOriginalPiece() { 
        return this.originalPiece;
    }

    public PieceButton getNewPiece() { 
        return this.newPiece;
    }

    public boolean getPieceSelected() {
        return this.pieceSelected;
    }

    public PieceButton[][] getButtons() {
        return this.buttons;
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public GameController getController() {
        return controller;
    }

    public void setOriginalPiece(PieceButton originalPiece) {
        this.originalPiece = originalPiece;
    }

    public void setNewPiece(PieceButton newPiece) {
        this.newPiece = newPiece;
    }

    public void setPieceSelected(boolean pieceSelected) {
        this.pieceSelected = pieceSelected;
    }

    public void setGameController(GameController controller) {
        this.controller = controller;
    }

    /* 
     * resizeBoard()
     * Done by:
     * Ashley Sim Ci Hui
     * 
     * @param boardPanel JPanel containing the board
     * @param boardContainer Another JPanel containing the boardPanel
     */

    private static void resizeBoard(JPanel boardPanel, JPanel boardContainer) {
        int width = boardContainer.getWidth();
        int height = boardContainer.getHeight();
        int size =  Math.min(width, height);
        boardPanel.setPreferredSize(new Dimension(size, size));
        boardContainer.revalidate();
    }

    /*
    buttonPanel()
    Done by:
    Javier Austin Anak Jawa
    Muhammad Zafran Bin Mohd Anuar

    This is the button panel for all the different functions (start, save, load, etc)
    @return buttonPanel
    */

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(150,80));

        // Display the buttons in a vertical manner
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create an empty border to center the buttons horizontally in the panel
        int padding = 44;
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        // Display the buttons vertically center of the panel.
        buttonPanel.add(Box.createVerticalGlue());

        JButton startButton = new JButton("Start");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() { /* Start button in GUI */
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setTurn(0);
                controller.setPlayer("yellow");
                updateDisplayPlayerTurn(controller);
                controller.setGameWin(false);
                controller.getBoard().init();
                //enable piece buttons
                changeButtonState(true);
                //enable save button
                saveButton.setEnabled(true);
                updatePieces();
            }
        });
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(10)); /* Sdd spacing between the buttons */

        saveButton.setEnabled(false); /* Save button disabled before game starts */
        saveButton.addActionListener(new ActionListener() { /* Save button in GUI */
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Save", JOptionPane.QUESTION_MESSAGE);
                controller.save(controller.getGameView(), filename);
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createVerticalStrut(10)); /* Add spacing between the buttons */

        loadButton.addActionListener(new ActionListener() { /* Load button in GUI */
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = JOptionPane.showInputDialog(null, "Enter save file name:", "Load", JOptionPane.QUESTION_MESSAGE);
                
                try {
                    controller.load(filename);
                    changeButtonState(true);
                    saveButton.setEnabled(true);
                    updatePieces();
                }
                catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "File could not be loaded.");
                    exc.printStackTrace();
                }

                updateDisplayPlayerTurn(controller);
            }
        });
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createVerticalStrut(10)); /* Add spacing between the buttons */

        helpButton.addActionListener(new ActionListener() {/* Help button in GUI */
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = controller.helpCommands();

                /* Check if the helpMessage is not null before showing the dialog */
                if (helpMessage != null)
                    JOptionPane.showMessageDialog(buttonPanel, helpMessage);
                else
                    JOptionPane.showMessageDialog(buttonPanel, "No help available.");
            }
        });
        buttonPanel.add(helpButton);
        buttonPanel.add(Box.createVerticalStrut(10)); /* Add spacing between the buttons */
        

        exitButton.addActionListener(new ActionListener() {/* Exit button in GUI */
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.exit();
            }
        });
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalStrut(10)); /* Add spacing between the buttons */

        /* Display the buttons vertically center of the panel. */
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }


    /* 
     * infoPanel()
     * Done by:
     * Javier Austin Anak Jawa
     * 
     * Info panel that shows whose turn it is
     * @param controller Game controller 
     * @return infoPanel
    */
    private JPanel infoPanel(GameController controller) {
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(150, 80));

        // Display the text in the center
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Create an empty border to center the text horizontally in the panel
        int padding = 21;
        infoPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        // Display the text vertically center of the panel.
        infoPanel.add(Box.createVerticalGlue());

        displayPlayerTurn = new JLabel("Player Turn: " + controller.getPlayer());
        infoPanel.add(displayPlayerTurn);

        // Display the text vertically center of the panel.
        infoPanel.add(Box.createVerticalGlue());

        return infoPanel;
    }


    /* 
    updatePlayerTurn()
    Done by:
    Javier Austin Anak Jawa

    Method to update the Player turn for every round
    @param controller Game Controller
    */

    public void updatePlayerTurn(GameController controller) {
        controller.changeTurns();
        updateDisplayPlayerTurn(controller);
    }

    public void updateDisplayPlayerTurn(GameController controller) {
        displayPlayerTurn.setText("Player Turn: " + controller.getPlayer());
    }

    /* 
    changeButtonState()
    Done by:
    Shashikaran A/L Kalairasu

    Enables/disables the buttons for the pieces on the board
    @param buttonState True/false depending on whether the buttons should be enabled/disabled
    */

    public void changeButtonState(boolean buttonState) {
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                buttons[i][j].setEnabled(buttonState);
            }
        }
    }

    /* 
    updatePieces()
    Done by:
    Ashley Sim Ci Hui

    Changes the icons on the board panel buttons based on the map attribute in the board
    */
    
    public void updatePieces() {
        Queue<Piece> pieces = new LinkedList<Piece>(); //queue data structure GG
        Component[] components = boardPanel.getComponents();//gets all components as component object

        for (int i = 0; i < controller.getBoard().getY(); i++) {
            for (Piece j : controller.getBoard().getMap().get(i)) {
                if (j != null)
                    pieces.add(j);// if not null fill with 
                else
                    pieces.add(null);
            }
        }

        for (Component component : components) { //for each loop 
            PieceButton button = (PieceButton) component;

            if (pieces.peek() != null) {
                Piece chosenPiece = pieces.peek();
                button.setPiece(chosenPiece);
                button.setIcon(new ImageIcon("Images/" + chosenPiece.getColour() + chosenPiece.getPieceName() + ".png")); //place where icon is mapped to button
                pieces.poll();
            }
            else {
                button.setPiece(null);
                button.setIcon(null);
                pieces.poll();
            }
        }
    }
}