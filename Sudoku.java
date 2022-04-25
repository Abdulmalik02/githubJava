package sudoku;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Stores a 9x9 Sudoku and implements basic operations to check rows, columns and 3x3 grids.
 * @author
 */
public class Sudoku implements Iterable<Cell>, Cloneable {
    private Cell[][] cells;
	private int numUnsolved;
	
	public Sudoku(int[][] puzzle) throws IllegalArgumentException {
		if(puzzle.length != 9) throw new IllegalArgumentException();
		cells = new Cell[9][9];
		numUnsolved = 0;
		for(int rowNum = 0; rowNum < puzzle.length; rowNum++) {
			initRow(rowNum, puzzle[rowNum]);
		}
	}
	
	public Sudoku(Cell[][] cells, int numUnsolved) {
		this.cells = cells;
		this.numUnsolved = numUnsolved;
	}
	
	private void initRow(int rowNum, int[] row) throws IllegalArgumentException {
		if(row.length != 9) throw new IllegalArgumentException();
		for(int colNum = 0; colNum < row.length; colNum++) {
			if(row[colNum] == 0) numUnsolved++;
			cells[rowNum][colNum] = new Cell(row[colNum], rowNum, colNum);
		}
	}
	
	public void set(int rowNum, int colNum, int num) throws IllegalArgumentException {
		if(num < 0 || num > 9) throw new IllegalArgumentException();
		cells[rowNum][colNum].set(num);
	}
	
	public int get(int rowNum, int colNum) {
		return cells[rowNum][colNum].get();
	}
	
	public int[] getRow(int rowNum) {
		int[] row = new int[9];
		for(int colNum = 0; colNum < 9; colNum++) {
			row[colNum] = cells[rowNum][colNum].get();
		}
		return row;
	}
	
	public int[] getCol(int colNum) {
		int[] column = new int[9];
		for(int rowNum = 0; rowNum < 9; rowNum++) {
			column[rowNum] = cells[rowNum][colNum].get();
		}
		return column;
	}
	
	public int[] getGrid(int rowNum, int colNum) {
		int[] sub = new int[9];
		int addOffsetX = 3 - ( (rowNum % 3) + 1 );
		int addOffsetY = 3 - ( (colNum % 3) + 1 ); ;
		int minusOffsetX = (rowNum % 3) ;
		int minusOffsetY = (colNum % 3) ;
		
		int index = 0;
		for(int row = rowNum - minusOffsetX; row < rowNum + addOffsetX + 1; row++) {
			for(int col = colNum - minusOffsetY; col < colNum + addOffsetY + 1; col++) {
				sub[index] = cells[row][col].get();
				index++;
			}
		}
		return sub;
	}
	
	/**
	 * Returns cell with fewest candidates.
	 * @return
	 */
	public Cell getEasiest() {
		Cell easiest = null;
		Iterator<Cell> iter = iterator();
		while(iter.hasNext()) {
			Cell c = iter.next();
			if(easiest == null && !c.isSolved()) easiest = c;
			if(!c.isSolved() && easiest.compareTo(c) < 0) easiest = c;
		}
		return easiest;
	}
	
	/**
	 * Returns number of unsolved cells in Sudoku.
	 * @return
	 */
	public int numUnsolved() {
		return numUnsolved;
	}
	
	/**
	 * Checks if column, row or grid contain duplicate numbers
	 * @param region Column, row or grid
	 * @return
	 */
	public boolean checkValid(int[] region) {
		boolean[] check = new boolean[9];
		for(int i = 0; i < region.length; i++) {
			if(region[i] == 0) ; //do nothing
			else if(check[region[i] - 1]) return false;
			else check[region[i] - 1] = true;
		}
		return true;
	}
	
	public boolean checkSolved() {
		Iterator<Cell> iter = iterator();
		while(iter.hasNext()) {
			Cell c = iter.next();
			if(!c.isSolved()) return false;
		}
		return true;
	}
	
	public boolean checkContradiction() {
		Iterator<Cell> iter = iterator();
		while(iter.hasNext()) {
			Cell c = iter.next();
			int[] row = getRow(c.getRowNum());
			int[] col = getCol(c.getColNum());
			int[] grid = getGrid(c.getRowNum(), c.getColNum());
			if(!checkValid(row) || !checkValid(col) || !checkValid(grid)) return true;
		}
		return false;
	}
	
	/**
	 * Performs one iteration of constraint propagation.  For each unsolved cell, checks
	 * the row, column and grid and removes those values from the list of candidates.
	 */
	private void propogate() {
		for(int rowNum = 0; rowNum < cells.length; rowNum++) {
			for(int colNum = 0; colNum < cells[rowNum].length; colNum++) {
				if(!cells[rowNum][colNum].isSolved()) {
					cells[rowNum][colNum].remove(getRow(rowNum));
					cells[rowNum][colNum].remove(getCol(colNum));
					cells[rowNum][colNum].remove(getGrid(rowNum,colNum));
					if(cells[rowNum][colNum].isSolved()) numUnsolved--;
				}
			}
		}
	}
	
	/**
	 * Loops constraint propagation
	 */
	public void constraintPropagation() {
		int prevNumUnsolved = -1;
		int newNumUnsolved = numUnsolved;
		while(prevNumUnsolved != newNumUnsolved) {
			propogate();
			prevNumUnsolved = newNumUnsolved;
			newNumUnsolved = numUnsolved();
		}
	}
	
	public Sudoku search(Sudoku puzzle) {
		puzzle.constraintPropagation();
		if(puzzle.checkContradiction()) return null;
		if(puzzle.checkSolved()) return puzzle;
		 
		//for cell with fewest candidates, loop over candidates
		Cell c = puzzle.getEasiest();
		while(c.numCandidates() > 0) {	
			int candidate = c.getCandidates()[0];
			Sudoku s = (Sudoku) puzzle.clone();
			s.set(c.getRowNum(), c.getColNum(), candidate);
			Sudoku solution = search(s);
			if(solution != null) {
				return solution;
			} else {
				c.remove(candidate);
			}
		}
		return null;
	}
	
	@Override
	public Object clone() {
		Cell[][] copy = new Cell[9][9];
		for(int rowNum = 0; rowNum < cells.length; rowNum++) {
			for(int colNum = 0; colNum < cells[rowNum].length; colNum++) {
				copy[rowNum][colNum] = (Cell) cells[rowNum][colNum].clone();
			}
		}
		Sudoku s = new Sudoku(copy, numUnsolved);
		return (Object) s;
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(cells);
	}

	@Override
	public Iterator<Cell> iterator() {
		return new SudokuIterator();
	}
	
	public class SudokuIterator implements Iterator<Cell> {
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return !(index == 80);
		}

		@Override
		public Cell next() {
			int row = (index / 9);
			int col = (index % 9);
			Cell toReturn = cells[row][col];
			index++;
			return toReturn;
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
}