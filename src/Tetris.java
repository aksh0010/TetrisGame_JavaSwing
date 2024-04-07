import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends JFrame {

    private JLabel statusBar;
    private Board board;

    public Tetris() {
    	 statusBar = new JLabel("0");
         statusBar.setFont(new Font("Arial", Font.BOLD, 24));
         statusBar.setHorizontalAlignment(SwingConstants.CENTER); // Center align status bar text
         statusBar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding around the text
         statusBar.setOpaque(true); // Make the label opaque to apply background color
         statusBar.setBackground(Color.DARK_GRAY); // Set background color
         statusBar.setForeground(Color.WHITE); // Set text color
         add(statusBar, BorderLayout.NORTH);

        board = new Board(this);
        add(board);

        setPreferredSize(new Dimension(200, 400)); // Set preferred size
        setTitle("My Tetris Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        BackgroundSound();
        createMenuBar(); // Create the menu bar
        showWelcomeDialog(); // Display welcome dialog before starting the game
    }
    private void BackgroundSound() {
		
		SoundPlayer.playSound("resources/tetris_game_background.wav",true);
	}
    private void showWelcomeDialog() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Tetris!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);

        JTextArea termsArea = new JTextArea(5, 20);
        termsArea.setText("Terms and Conditions:\n\n"
                + "1. Play responsibly.\n"
                + "2. Enjoy the game!\n\n"
                + "By checking the box below, you agree to the terms and conditions.");
        termsArea.setEditable(false);
        termsArea.setLineWrap(true);
        termsArea.setWrapStyleWord(true);
        welcomePanel.add(new JScrollPane(termsArea), BorderLayout.CENTER);

        JCheckBox agreeCheckbox = new JCheckBox("I agree to the terms and conditions");
        welcomePanel.add(agreeCheckbox, BorderLayout.SOUTH);

        int option = JOptionPane.showConfirmDialog(this, welcomePanel, "Welcome", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION && agreeCheckbox.isSelected()) {
            startGame(); // Start the game if user agrees
        } else {
            System.exit(0); // Close the application if user cancels or doesn't agree
        }
    }
    public JLabel getStatusBar() {
        return statusBar;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Game menu
        JMenu gameMenu = new JMenu("Game");

        JMenuItem startItem = new JMenuItem("Start");
        startItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        gameMenu.add(startItem);

        JMenuItem restartItem = new JMenuItem("Restart");
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	statusBar.setText("0");
                board.clearBoard(); // Clear the board
                startGame();
            }
        });
        gameMenu.add(restartItem);

        JMenuItem pauseItem = new JMenuItem("Pause/Resume");
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.pause(); // Pause or resume the game
            }
        });
        gameMenu.add(pauseItem);

        // Instructions menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
        helpMenu.add(instructionsItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar); // Set the menu bar to the frame
    }

    private void startGame() {
        board.start();
        board.newPiece();
    }

    private void showInstructions() {
        String instructions = "Welcome to Tetris!\n\n"
                + "Controls:\n"
                + "Left Arrow: Move Left\n"
                + "Right Arrow: Move Right\n"
                + "Up Arrow: Rotate\n"
                + "Down Arrow: Move Down\n"
                + "Space: Drop\n"
                + "P/p: Pause/Resume\n"
                + "D/d: Move down quickly\n\n"
                + "Press 'Start' to begin!";

        JOptionPane.showMessageDialog(this, instructions, "Tetris Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tetris t = new Tetris();
            t.pack(); // Pack components
            t.setLocationRelativeTo(null); // Center the frame
            t.setVisible(true);
        });
    }
}
