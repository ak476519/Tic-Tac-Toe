package ticTacToe;

import ticTacToe.Cell.CellType;

public class Board {
	
	private Cell[][] cells = new Cell[4][4]; //Location of each cell in a 3x3 board (start counting from row and column 1, not 0)
	private int currentPlayer; // == 0 for "X" and == 1 for "O"
	
	/** Initialize the 9 cells in the board, and all object variables */
	public Board() {
		for (int i = 1; i < 4; ++i) {
			for (int j = 1; j < 4; ++j) {
				cells[i][j] = new Cell(CellType.BLANK);
			}
		}
	}

	public int checkBoard(int row, int col) {
		if (cells[row][col].getValue() == CellType.X) {
			return 1;
		}
		if (cells[row][col].getValue() == CellType.O) {
			return 2;
		}
		return 0;
	}
	
	/** Has the current player won on any row? */
	public boolean checkRows( CellType playerTag ) {
		for (int i = 1; i < 4; ++i) {
			if (cells[i][1].getValue() != playerTag) continue;
			if ((cells[i][1].getValue() == cells[i][2].getValue()) && (cells[i][1].getValue() == cells[i][3].getValue())) return true;
		}
		return false;
	}

	/** Has the current player won on any column? */
	public boolean checkColumns( CellType playerTag ) {
		for (int i = 1; i < 4; ++i) { 
			if (cells[1][i].getValue() != playerTag) continue;
			if ((cells[1][i].getValue() == cells[2][i].getValue()) && (cells[1][i].getValue() == cells[3][i].getValue())) return true;
		}
		return false;
	}

	/** Has the current player won on any diagonal? */
	public boolean checkDiagonals( CellType playerTag ) {
		if (cells[1][1].getValue() == playerTag) {
			if ((cells[1][1].getValue() == cells[2][2].getValue()) && (cells[1][1].getValue() == cells[3][3].getValue())) return true;
		}
		if (cells[1][3].getValue() == playerTag) {
			if ((cells[1][3].getValue() == cells[2][2].getValue()) && (cells[1][3].getValue() == cells[3][1].getValue())) return true;
		}
		return false;
	}

	/** Check which player's turn it is */
	public CellType checkCurrentPlayer(int currentPlayer) {
		if (currentPlayer == 0) {
			return CellType.X;
		} else {
			return CellType.O;
		}
	}

	/** Check which player is not playing */
	public CellType checkOtherPlayer(int currentPlayer) {
		if (currentPlayer == 0) {
			return CellType.O;
		} else {
			return CellType.X;
		}
	}
	/** Check if the current player has won the game with their move. */
	public boolean checkForWin( ) {
		CellType playerTag = checkOtherPlayer(currentPlayer); //use checkOtherPlayer because the current turn has been switched in makeMove method
		return (checkRows(playerTag) || checkColumns(playerTag) || checkDiagonals(playerTag));
	}
	
	/** Returns true if all cells are taken, but no one has won. */
	public boolean checkForTie( ) {
		if (checkForWin()) return false;
		for (int i = 1; i < 4; ++i) {
			for (int j = 1; j < 4; ++j) {
				if (cells[i][j].getValue() == CellType.BLANK) return false;
			}
		}
		return true;
	}
	
	/** Process a move that goes into the cell (row, column).
	 *  That includes switching whose turn it is now.         */
	public void makeMove(int row, int col) {
		CellType playerTag = checkCurrentPlayer(currentPlayer);
		cells[row][col].setValue(playerTag);
		if (currentPlayer == 0) { //switch turn
			currentPlayer = 1;
		} else {
			currentPlayer = 0;
		}
	}

}
