import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import javax.swing.Timer;
import javax.swing.plaf.basic.BasicBorders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

//import Shape.Tetromions;

import java.awt.*;
//import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{

	private static final int BOARD_WIDTH=10 , BOARD_HEIGHT=22;
	private static final Color[] COLORS= {new Color(0,0,0),new Color(204,102,102),
		new Color(102,204,102),
		new Color(102,102,104),
		new Color(204,204,102),
		new Color(204,102,204),
		new Color(102,204,204),
		new Color(218,170,0)};
	
	private Timer timer;
	private boolean isFallingFinished=false;
	private boolean isStarted=false;
	private boolean isPaused=false;
	
	
	private int numLinesRemoved=0;
	private int curX=0;
	private int curY=0;
	private JLabel statusBar;
	private Shape curPiece;
	private Tetromions [] board;
	
	public Board(Tetris parent) {
		setFocusable(true);
		curPiece = new Shape();
		timer = new Timer(400,this); // Timer for lines down
		statusBar = parent.getStatusBar();
		board  = new Tetromions[BOARD_WIDTH*BOARD_HEIGHT];
		clearBoard();
		addKeyListener(new MyTetrisAdapter());
	}

	public int squareWidth() {
		
		return (int) getSize().getWidth()/BOARD_WIDTH;
	}
	public int squareHeight() {
		
		return (int) getSize().getWidth()/BOARD_HEIGHT;
	}
	
	public Tetromions shapeAt(int x, int y) {
		
		
		return board[y*BOARD_WIDTH +x];
	}
	
	public void clearBoard() {
		
		for ( int i=0;i<BOARD_WIDTH*BOARD_HEIGHT;i++) {
			
			board[i]= Tetromions.NoShape;
		}
	}
	
	private void pieceDropped() {
		for(int i =0;i<4;i++) {
			
			int x = curX + curPiece.x(i);

			int y = curX + curPiece.y(i);
			board[y*BOARD_WIDTH+x]= curPiece.getShape();
			
		}

		removeFullLines();

		if (!isFallingFinished) {
			
			newPiece();
			
		}
	}
	
	public void newPiece() {
		
		curPiece.setRandomShape();
		curX = BOARD_WIDTH / 2 + 1;
		curY= BOARD_HEIGHT - 1 + curPiece.minY();
		
		if(!tryMove(curPiece, curX, curY - 1)){
			curPiece.setShape(Tetromions.NoShape);
			timer.stop();
			isStarted = false;
			statusBar.setText("Game Over");
		}
	}
	
	private void onLineDown() {
		if(!tryMove(curPiece, curX, curY - 1)){
			pieceDropped();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(isFallingFinished) {
			
			isFallingFinished =false;
			newPiece();
		}
		else {
			onLineDown();
		}
	}

	//Drawing Tetromions
	
	private void drawSquare(Graphics g,int x , int y , Tetromions shape) {
		Color  color = COLORS[shape.ordinal()];
		
		g.setColor(color);
		g.fillRect(x+1, y+1, squareWidth()-2, squareHeight()-2);
		
		g.setColor(color.brighter());
		g.drawLine(x, y+squareHeight()-1, x, y);
		g.drawLine(x, y, x+squareWidth()-1, y);
		g.setColor(color.darker());
		g.drawLine(x+1, y+squareHeight()-1, x+squareWidth()-1, y+squareHeight()-1);
		g.drawLine(x+squareWidth()-1, y + squareHeight()-1, x + squareWidth()-1, y+1);
	}
	//painting the board
	@Override
	public void paint(Graphics g) {
    	super.paint(g); // call the superclass paint method to ensure proper painting behaviour

    	Dimension size = getSize(); //Get the size of the panel

    	int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight(); //Calculate the top position of the board


		//Iterate  over each row and column of the board
    	for (int i = 0; i < BOARD_HEIGHT; i++) {

        	for (int j = 0; j < BOARD_WIDTH; j++) {

            	Tetromions shape = shapeAt(j, BOARD_HEIGHT - i - 1);

            	if (shape != Tetromions.NoShape) {

					//Draw the square at the correct position on the board

                	drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
            	}
        	}
    	}

    	if (curPiece.getShape() != Tetromions.NoShape) {
			//If there is a current piece being displayed 
        	for (int i = 0; i < 4; i++) {

            	int x = curX + curPiece.x(i); //Calculate the X coordinate of the current block

            	int y = curY - curPiece.y(i); // Adjust Y-coordinate to be subtracted

				//Draw the square at the correct position on the board adjusted for the top position and Y-coordinate

            	drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), curPiece.getShape());
        	}
    	}	
	}

	//Method to start the game
	public void start(){
		if(isPaused)
			return;
		
		isStarted = true;

		isFallingFinished = false;

		numLinesRemoved = 0;

		clearBoard();

		newPiece();

		timer.start(); // timer will start swing timer that will call actionPerformed

	}

	//method to pause the game

	public void pause(){
		if(!isStarted)
			return;
		
		isPaused = !isPaused;


		if(isPaused){
			timer.stop();
			statusBar.setText("Paused...");
		} else {
			timer.start();
			statusBar.setText(String.valueOf(numLinesRemoved));
		}

		repaint();

	}
	

	//Method to try moving a piece to a new position 
	private boolean tryMove(Shape newPiece, int newX, int newY){
		for(int i = 0; i < 4; i++ ){
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);

			if(x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT){
				return false;
			}

			if(shapeAt(newX, newY) != Tetromions.NoShape){
				return false;
			}


		}

		curPiece = newPiece;
		curX = newX;
		curY = newY;
		repaint();


		return true;
	}

	private void removeFullLines(){
		int numFullLines = 0;

		for(int i = BOARD_HEIGHT - 1; i >= 0; --i){
			boolean lineIsFull = true;

			for(int j = 0; j < BOARD_WIDTH; ++j){
				if(shapeAt(i, j) == Tetromions.NoShape){
					lineIsFull = false;
					break;
				}
			}

			if(lineIsFull){
				++numFullLines;

				for(int k = i; k < BOARD_HEIGHT - 1;  ++k){
					for(int j = 0; j < BOARD_WIDTH; ++j){
						board[k * BOARD_WIDTH + j] = shapeAt(j, k + 1);
					}
				}
			}
				

			if(numFullLines > 0){
					numLinesRemoved += numFullLines;
					statusBar.setText(String.valueOf(numLinesRemoved));
					isFallingFinished = true;
					curPiece. setShape(Tetromions.NoShape);
					repaint();
			}
		}
	}

	// Adding drop down method
	private void dropDown(){
		int newY  = curY;

		while (newY > 0){
			if(!tryMove(curPiece, curX, newY - 1)){
				break;
			}

			--newY;
		}

		pieceDropped();
	}


	class MyTetrisAdapter extends KeyAdapter{
		@Override
		public void keyPressed (KeyEvent ke){
			if(!isStarted || curPiece.getShape() == Tetromions.NoShape){
				return;
			}

			int keyCode = ke.getKeyCode();

			if(keyCode == 'p' || keyCode == 'p'){
				pause();
			}
			if(isPaused){
				return;
			}

			switch(keyCode){
				case KeyEvent.VK_LEFT:
					tryMove(curPiece, curX - 1, curY);
				break;

				case KeyEvent.VK_RIGHT:
					tryMove(curPiece, curX + 1, curY);
				break;

				case KeyEvent.VK_DOWN:
					tryMove(curPiece.rotateRight(), curX, curY);
				break;

				case KeyEvent.VK_UP:
					tryMove(curPiece.rotateLeft(), curX, curY);
				break;

				case KeyEvent.VK_SPACE:
					dropDown();
				break;

				case 'd':
					onLineDown();
				break;

				case 'D':
					onLineDown();
				break;



			}
		}
	}

	
}