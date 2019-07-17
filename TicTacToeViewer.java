package ticTacToe;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacToeViewer {
	
		private static JLabel statusBox;
	    private static JFrame appFrame = new JFrame();

		public static void setLabelText( String newText ) {
			statusBox.setText( newText );
		}

		public static void refresh() {
		    appFrame.getContentPane().validate();
		    appFrame.getContentPane().repaint();
		}
		
	   public static void main(String[] args) {
		      TicTacToeComponent ticTacToeComponent = new TicTacToeComponent();
		      statusBox = new JLabel("It's X's turn to play.");

		      appFrame.setSize(400, 400);
		      appFrame.setTitle("Tic-Tac-Toe Viewer");
		      appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      
		      // Add the two GUI elements to the frame
		      appFrame.getContentPane().add( statusBox, BorderLayout.SOUTH);
		      appFrame.getContentPane().add(ticTacToeComponent, BorderLayout.CENTER);
		      ticTacToeComponent.addMouseListener(ticTacToeComponent);
		      

		      // Set the frame and its contents visible
		      appFrame.setVisible(true);
		   }
}