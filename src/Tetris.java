
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame{

	private JLabel statusBar;
	
	public Tetris() {
		statusBar = new JLabel("0"); // To display line no
		add(statusBar,BorderLayout.NORTH);
		Board board = new Board(this);
		add (board);
		//hello
		//add one piece
		board.newPiece();
		board.repaint();
		
		setSize(200,400); // setting size
		setTitle("My Tetris Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public JLabel getStatusBar() {
		
		return statusBar;
	}
	
	public static void main(String[] args) {
		
		Tetris t = new Tetris();
		t.setLocationRelativeTo(null); // center
		t.setVisible(true);
		
		
	}
	
}
