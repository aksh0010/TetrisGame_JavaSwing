import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
public class Tetris extends JFrame{

	private JLabel statusBar;
	
	public Tetris() {
		statusBar = new JLabel("0"); // To display line no
		statusBar.setFont(new Font("Arial", Font.BOLD, 24));
		add(statusBar,BorderLayout.NORTH);
		Board board = new Board(this);
		add (board);
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
		 // Show instructions in a dialog
        JOptionPane.showMessageDialog(this, instructions, "Tetris Instructions", JOptionPane.INFORMATION_MESSAGE);

        // Add a button to start the game
        int option = JOptionPane.showConfirmDialog(this, "Ready to start?", "Start Game", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
        	//Starting lines down
    		board.start();
    		
    		//add one piece
    		board.newPiece();
    		board.repaint();
    		
    		setSize(200,400); // setting size
    		setTitle("My Tetris Game");
    		setDefaultCloseOperation(EXIT_ON_CLOSE);
        } else {
            System.exit(0);
        }
	
	}
	
	public JLabel getStatusBar() {
		
		return statusBar;
	}
	
	public static void main(String[] args) {
		
		Tetris t = new Tetris();
		t.setLocationRelativeTo(null); // center
		t.setVisible(true);
		
		// Testing

		//Now we add interaction to play 

		
		
		
	}
	
}
