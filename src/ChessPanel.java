import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	private int x, y;// these are the coords where I display the image above
	List<Point>clickList = new ArrayList<Point>();
	final int WIDTH = 800, HEIGHT = 600;
	Board b;
	
	public ChessPanel() throws IOException{
	
		
	}
private void setUpMouseListener() {
		
		
		// panels (and other JComponents) have a built in mechanism to interface between 
		// the hardware and OS and our application.  This basically asks the Panel to add
		// a MouseListener to its list of MouseListeners (by default this list is empty... I think)
		// When the OS detects a change in the state of the mouse, it alerts the JVM, which passes
		// that info along to the application with 'focus'.  Our Panel will, in turn, alert everybody
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
			public void clickedOnPiece (MouseEvent arg0){
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("Just entered: "+arg0);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//System.out.println("Just exited: "+arg0);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("Just pressed: "+e);
				x = e.getX();
				y = e.getY();
				clickList.add(e.getPoint());
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Just released: "+e);
			}
			
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		b.draw(g);
	}
}

