import java.awt.Graphics;

public abstract class Piece {
	public String s;
	boolean isCaptured;
	int x;
	int y;
	boolean isWhite;
	
	public Piece(int a, int b, boolean c) {
		x = a;
		y = b;
		isWhite = c;
	}
	public abstract boolean isLegal(int q, int w);
	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString(){
		if 
	}

}
