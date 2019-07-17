package ticTacToe;

public class Cell {
	
	public enum CellType { BLANK, O, X };
	private CellType value;
	
	/** Constructor */
	public Cell( CellType startValue ) {
		value = startValue;
	}
	
	/** Standard "toString" method. Works well with the debugger. */
	@Override
	public String toString( ) {
		switch (value) {
		case O:
			return "O";
		case X:
			return "X";
		default:
			return "_";
		}
	}

	/** Standard getter */
	public CellType getValue() {
		return value;
	}

	/** Standard setter */
	public void setValue(CellType value) {
		this.value = value;
	}
	
}
