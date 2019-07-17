package ticTacToe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

// HistogramComponent extends the functionality of a JComponent
// in order to draw a histogram.

@SuppressWarnings("serial")
public class TicTacToeComponent extends JComponent implements MouseListener {

	int x = 10;
	int n = 100;
	int p = 10;
	int y = 150;
	int k = 15;
    
	public static Board gameBoard = new Board(); // initialize the game
	public static boolean gameOver = false; // keep track when the game is over
	char currentPlayer = 'X';
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {
		int xCoordinate = e.getX();
		int yCoordinate = e.getY();
		int row = 0;
		int col = 0;
		if ((xCoordinate < x) || (yCoordinate < y) || (xCoordinate > x + 3 * n + 2 * p) || (yCoordinate > y + 3 * n + 2 * p)) return;
		//row
		if (yCoordinate <= y + n + p) {
			row = 1;
		} else {
			if (yCoordinate <= y + 2 * n + 2 * p) {
				row = 2;
			} else {
				row = 3;
			}
		}
		//column
		if (xCoordinate <= x + n + p) {
			col = 1;
		} else {
			if (xCoordinate <= x + 2 * n + 2 * p) {
				col = 2;
			} else {
				col = 3;
			}
		}
		if (gameBoard.checkBoard(row, col) != 0) return;
		gameBoard.makeMove(row, col);
	    gameOver = (gameBoard.checkForWin()) || gameBoard.checkForTie();
	    if (!gameOver) {
		    currentPlayer = (currentPlayer == 'X')? 'O':'X';
			TicTacToeViewer.setLabelText( "It's " + currentPlayer + "'s turn to play." );
	    } else {
	    	if (gameBoard.checkForWin()) {
	    		TicTacToeViewer.setLabelText("Player " + currentPlayer + " won the game!");
	    	} else {
	    		TicTacToeViewer.setLabelText("This game ends in a tie.");
	    	}
	    }
	    TicTacToeViewer.refresh();
	}
	
	public void paintComponent(Graphics g) {

		Graphics2D graphicsObj = (Graphics2D) g;

		Rectangle binRectangle1 = new Rectangle(x, y + n, 3 * n + 2 * p, p);
		Color binColor = new Color(150, 100, 100);
		graphicsObj.setColor(binColor);
		graphicsObj.fill(binRectangle1);

		Rectangle binRectangle2 = new Rectangle(x, y + 2 * n + p, 3 * n + 2 * p, p);
		graphicsObj.fill(binRectangle2);

		Rectangle binRectangle3 = new Rectangle(x + n, y, p, 3 * n + 2 * p);
		graphicsObj.fill(binRectangle3);

		Rectangle binRectangle4 = new Rectangle(x + 2 * n + p, y, p, 3 * n + 2 * p);
		graphicsObj.fill(binRectangle4);
		
		for (int row = 1; row < 4; ++row) {
			for (int col = 1; col < 4; ++col) { 
				
				int left = x + (col - 1) * n + (col - 1) * p + k;
				int right = x + col * n + (col - 1) * p - k;
				int upper = y + (row - 1) * n + (row - 1) * p + k;
				int lower = y + row * n + (row - 1) * p - k;
				
				if (gameBoard.checkBoard(row, col) == 1) {
					Color binColorX = new Color(128, 128, 200);
					graphicsObj.setColor(binColorX);
					graphicsObj.setStroke(new BasicStroke(p));
					graphicsObj.drawLine(left, upper, right, lower);
					graphicsObj.drawLine(right, upper, left, lower);
				}
				
				if (gameBoard.checkBoard(row, col) == 2) {
					Color binColorO = new Color(50, 200, 50);
					graphicsObj.setColor(binColorO);
					graphicsObj.setStroke(new BasicStroke(p));
					graphicsObj.drawOval(left, upper, n - 2 * k, n - 2 * k);
				}
			}
		}
	}
}