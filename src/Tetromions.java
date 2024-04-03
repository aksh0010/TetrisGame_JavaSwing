

public enum Tetromions {

	NoShape(new int[][] {{0,0},{0,0},{0,0},{0,0}}),
	ZShape(new int[][] {{0,-1},{0,0},{-1,0},{-1,1}}),
	SShape(new int[][] {{0,-1},{0,0},{1,0},{1,1}}),
	LineShape(new int[][] {{0,-1},{0,0},{0,1},{0,2}}),
	TShape(new int[][] {{-1,0},{0,0},{1,0},{0,1}}),
	SquareShape(new int[][] {{0,0},{1,0},{0,1},{1,1}}),
	LShape(new int[][] {{-1,-1},{0,-1},{0,0},{0,1}}),
	MirroredShape(new int[][] {{1,-1},{0,-1},{0,0},{0,1}});
	
	
	public int [][] coords; // X, y coordinates for the shapes
	
	
	private Tetromions (int [][] coords) {
		
		this.coords = coords;
		
	}
}
