import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GraphicsPanel extends JPanel {
	
	final int WIDTH = 800, HEIGHT = 600;// starting values for width and height in pixels
	JButton coolButton = new JButton("Cool!");// I could have just declared here...
	JTextField textField = new JTextField(20);// put in different numbers and see what happens
	JLabel label = new JLabel();// labels are dynamic and great ways to display stuff
	JTextArea textArea = new JTextArea(40,40);// these are great for displaying lots of text
	Image image;  // reference to an Image to be displayed.  Opened later, but could be opened here
	private int numPaints;// just a counter that I used to show what events cause a call to paintComponent
	private int x=50, y=200;// these are the coords where I display the image above
	List<Point>clickList = new ArrayList<Point>();// I wanted to keep track of all the places I clicked
													// so a List made sense
	private Random die = new Random();// I wanted randomness (specifically for colors)

	public GraphicsPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));// Otherwise, the frame starts off small
		//setUpGridLayout(2,2);// you can arrange the components in a grid
		setUpBorderLayout();// This places things around the border or center
		//this.setUpWithNullLayout();// This gives you more freedom, but you lose the dynamic nature 
									// that a layout manager gives.
		setUpButtons();// or just one button.  The code written in this method is 
						// generally accepted as a nice approach to linking the button with event launch
		setUpMouseListener();// Again, this is a great way to acquire mouse actions and react to them
							// Java also provides a MouseMotionListener which makes reacting to mouse dragging easier
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
				System.out.println("Just clicked: "+arg0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("Just entered: "+arg0);
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				System.out.println("Just exited: "+arg0);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Just pressed: "+e);
				x = e.getX();
				y = e.getY();
				clickList.add(e.getPoint());
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Just released: "+e);
			}
			
		});
	}


	private void setUpButtons() {
		coolButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// What do you want to do when the button is clicked????
				File f = selectFile();
				tryOpenImage(f);
				repaint();
			}
			
		});
	}


	protected File selectFile() {
		JFileChooser jfc = new JFileChooser();// File chooser
		jfc.showOpenDialog(null);
		File f = jfc.getSelectedFile();// gets the file that the user selected
		System.out.println(f);
		
		try {
			Scanner scan = new Scanner(f);// some kinds of files you want to scan
			// depending on the file you open, the output may be strange!
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}


	protected void tryOpenImage(File f) {
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(image!=null) {
			label.setIcon(new ImageIcon(f.getAbsolutePath()));
			// this is a nice way to draw images, using labels and slapping an icon on it
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		System.out.println(numPaints++);
//		// all the code required to repaint this panel ... this will 
//		// be called in the beginning and any time a change has been 
//		// made to the view or something has changed this panel (resizing, 
//		// another window has covered it and then uncovered it...
//		
//		g.drawImage(image, x, y, null);// or could pass this as last argument
//		g.drawImage(image, 0, 300, 200, 100, this);
//		Color c = new Color(die.nextInt(256),die.nextInt(256),die .nextInt(256));
//		g.setColor(c);
//		Point start = null;
//		for(Point p:clickList) {
//			if(start !=null) {
//				g.drawLine(start.x, start.y, p.x, p.y);
//			}
//			start = p;
//		}
	}

	private void setUpBorderLayout() {
		this.add(coolButton, BorderLayout.EAST);
		this.add(textField, BorderLayout.SOUTH);// notice the different cursor locations...
		this.add(label, BorderLayout.CENTER);
		this.add(textArea, BorderLayout.WEST);// notice the different cursor locations...
		textArea.setBackground(new Color(200,100, 150));
		textArea.setForeground(Color.green);
		this.setBackground(new Color(100, 200, 150));
		
	}


	private void setUpGridLayout(int rows, int cols) {
		this.setLayout(new GridLayout(rows,cols));
		// what happens if I add them in different order???
		this.add(coolButton);
		this.add(textField);// notice the different cursor locations...
		this.add(label);
		this.add(textArea);// notice the different cursor locations...
		textArea.setBackground(new Color(200,100, 150));
		textArea.setForeground(Color.green);
		this.setBackground(new Color(100, 200, 150));
	}
	
	private void setUpWithNullLayout() {
/* USE WITH CAUTION!!!!  JAVA HAS DONE A GREAT JOB building a responsive, dynamic 
   containers and you should really use one (or many) of their layout managers
   That being said, when I started using layouts, I got frustrated and would tend
   to make my layout null and I would place the components where I wanted them...
   */
		this.setLayout(null);
		// now, use setBounds to place the components.  You also have greater control
		// over the sizes of the components
		this.add(coolButton);
		coolButton.setBounds(100, 500, 200, 150);// upper-left-hand corner is (100,500)
												// with a width of 200 and height of 150
		this.add(textField);// notice the different cursor locations...
		textField.setBounds(600, 300,100, 50);
		this.add(label);
		label.setBounds(400, 300, 400,300);
		this.add(textArea);// notice the different cursor locations...
		textArea.setBounds(300, 100, 100,500);
		textArea.setBackground(new Color(200,100, 150));
		textArea.setForeground(Color.green);
		this.setBackground(new Color(100, 200, 150));
	}
	
}
