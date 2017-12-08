import java.io.IOException;
import javax.swing.JFrame;

public class Game extends JFrame {
	public static void main(String[] args) throws IOException {
		
		new Game();
		
	}
	
	public Game() throws IOException {
		super("SOLITAIRE");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		this.add(panel);
		this.pack();
		this.setVisible(true);
		
	}

}
