
import java.util.Random;

public class Shape {

	private Tetromions pieceShape;
	private int[][] coords;
	
	public Shape() {
		
		coords= new int[4][2];
		setShape(Tetromions.NoShape);
	}
	public void setShape(Tetromions shape) {// check done
		
		
		for (int i=0; i<4;i++) {
			
			
			for(int j=0; j<2;++j) {
				
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
	
	public int x(int index) {
		
		return coords[index][0];
		
		
	}
	public int y(int index) {
		
		return coords[index][1];
		
		
	}
	public Tetromions getShape() {
		
		return pieceShape;
		
	}
	public void setRandomShape() {
		
		Random random = new Random();
		int x = Math.abs(random.nextInt()) %7+1;
		
		Tetromions[] values= Tetromions.values();
		setShape(values[x]);
		
	}
	public int minX() {// check done
		
		int m = coords[0][0];
		
		for (int i =0;i<4;i++) {
			
			m=Math.min(m, coords[i][0]);
		}
		return m;
	}
	
	public int minY() { // check done
		
		int m = coords[0][1];
		
		for (int i =0;i<4;i++) {
			
			m=Math.min(m, coords[i][1]);
		}
		return m;
	}

	//To rotate shapes
	public Shape rotateLeft(){
		if(pieceShape == Tetromions.SquareShape){
			return this;
		}
		
		Shape result =  new Shape();
		result.pieceShape = pieceShape;

		for(int i  = 0; i < 4; i++){

			result.setX(i, y(i));
			result.setY(i, -x(i));

		}
		return result;

	}

	public Shape rotateRight(){
		if(pieceShape == Tetromions.SquareShape){
			return this;
		}
		
		Shape result =  new Shape();
		result.pieceShape = pieceShape;

		for(int i  = 0; i < 4; i++){

			result.setX(i, -y(i));
			result.setY(i, x(i));

		}
		return result;

	}






}