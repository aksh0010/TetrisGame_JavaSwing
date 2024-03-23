
import java.util.Random;

public class Shape {

	enum Tetromions{
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
			
		}}
	
	private Tetromions pieceShape;
	private int[][] coords;
	
	public Shape() {
		
		coords= new int[4][2];
		setShape(Tetromions.NoShape);
	}
	public void setShape(Tetromions shape) {
		
		
		for (int i=0; i<4;i++) {
			
			
			for(int j=0; j<2;j++) {
				
				coords[i][j]= shape.coords[i][j];
				
			}
		}
	pieceShape = shape;
	}
	
	private void setX(int index, int x) {
		
	coords[index][0]=x;	
	}
	private void setY(int index, int y) {
		
	coords[index][1]=y;	
	}
	
	private int x(int index) {
		
		return coords[index][0];
		
		
	}
	private int y(int index) {
		
		return coords[index][1];
		
		
	}
	public Tetromions getShape() {
		
		return pieceShape;
		
	}
	public void setRandomShape() {
		
		Random random = new Random();
		int x = Math.abs(random.nextInt());
		
		Tetromions[] values= Tetromions.values();
		setShape(values[x]);
		
	}
	public int minX() {
		
		int m = coords[0][0];
		
		for (int i =0;i<4;i++) {
			
			m=Math.min(m, coords[i][0]);
		}
		return m;
	}
	
	public int minY() {
		
		int m = coords[0][1];
		
		for (int i =0;i<4;i++) {
			
			m=Math.min(m, coords[i][1]);
		}
		return m;
	}
}
