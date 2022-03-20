import java.util.*;

/*
 * Encapsulates a Sudoku grid to be solved.
 * CS108 Stanford.
 */
public class Sudoku {

	public class Spot {

		private int row;
		private int col;
		private int number;

		private Spot(int row, int col, int number){
			this.row = row;
			this.col = col;
			this.number = number;
		}

		private int getRow() {
			return row+1;
		}

		private int getCol() {
			return col+1;
		}

		private void set(int number) {
			this.number = number;
		}

		private int getNumber() {
			return number;
		}

//		//for Tests
//		public int getRow() {
//			return row+1;
//		}
//
//		public int getCol() {
//			return col+1;
//		}
//
//		public void set(int number) {
//			this.number = number;
//		}
//
//		public int getNumber() {
//			return number;
//		}
	}

	private int[][] startingPuzzle;

	public Spot[][] puzzle;

	private String solutionText;

	private long saveStartTime;
	private long saveLastTime;


//	//for tests.
//	public ArrayList<Spot> spotSorted;

	// Provided grid data for main/testing
	// The instance variable strategy is up to you.
	
	// Provided easy 1 6 grid
	// (can paste this text into the GUI too)
	public static final int[][] easyGrid = Sudoku.stringsToGrid(
	"1 6 4 0 0 0 0 0 2",
	"2 0 0 4 0 3 9 1 0",
	"0 0 5 0 8 0 4 0 7",
	"0 9 0 0 0 6 5 0 0",
	"5 0 0 1 0 2 0 0 8",
	"0 0 8 9 0 0 0 3 0",
	"8 0 9 0 4 0 2 0 0",
	"0 7 3 5 0 9 0 0 1",
	"4 0 0 0 0 0 6 7 9");
	
	
	// Provided medium 5 3 grid
	public static final int[][] mediumGrid = Sudoku.stringsToGrid(
	 "530070000",
	 "600195000",
	 "098000060",
	 "800060003",
	 "400803001",
	 "700020006",
	 "060000280",
	 "000419005",
	 "000080079");
	
	// Provided hard 3 7 grid
	// 1 solution this way, 6 solutions if the 7 is changed to 0
	public static final int[][] hardGrid = Sudoku.stringsToGrid(
	"3 7 0 0 0 0 0 8 0",
			"0 0 1 0 9 3 0 0 0",
			"0 4 0 7 8 0 0 0 3",
			"0 9 3 8 0 0 0 1 2",
			"0 0 0 0 4 0 0 0 0",
			"5 2 0 0 0 6 7 9 0",
			"6 0 0 0 2 1 0 4 0",
			"0 0 0 5 3 0 9 0 0",
			"0 3 0 0 0 0 0 5 1");
	
	
	public static final int SIZE = 9;  // size of the whole 9x9 puzzle
	public static final int PART = 3;  // size of each 3x3 part
	public static final int MAX_SOLUTIONS = 100;
	
	// Provided various static utility methods to
	// convert data formats to int[][] grid.
	
	/**
	 * Returns a 2-d grid parsed from strings, one string per row.
	 * The "..." is a Java 5 feature that essentially
	 * makes "rows" a String[] array.
	 * (provided utility)
	 * @param rows array of row strings
	 * @return grid
	 */
	public static int[][] stringsToGrid(String... rows) {
		int[][] result = new int[rows.length][];
		for (int row = 0; row<rows.length; row++) {
			result[row] = stringToInts(rows[row]);
		}
		return result;
	}
	
	
	/**
	 * Given a single string containing 81 numbers, returns a 9x9 grid.
	 * Skips all the non-numbers in the text.
	 * (provided utility)
	 * @param text string of 81 numbers
	 * @return grid
	 */
	public static int[][] textToGrid(String text) {
		int[] nums = stringToInts(text);
		if (nums.length != SIZE*SIZE) {
			throw new RuntimeException("Needed 81 numbers, but got:" + nums.length);
		}
		
		int[][] result = new int[SIZE][SIZE];
		int count = 0;
		for (int row = 0; row<SIZE; row++) {
			for (int col=0; col<SIZE; col++) {
				result[row][col] = nums[count];
				count++;
			}
		}
		return result;
	}
	
	
	/**
	 * Given a string containing digits, like "1 23 4",
	 * returns an int[] of those digits {1 2 3 4}.
	 * (provided utility)
	 * @param string string containing ints
	 * @return array of ints
	 */
	public static int[] stringToInts(String string) {
		int[] a = new int[string.length()];
		int found = 0;
		for (int i=0; i<string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				a[found] = Integer.parseInt(string.substring(i, i+1));
				found++;
			}
		}
		int[] result = new int[found];
		System.arraycopy(a, 0, result, 0, found);
		return result;
	}


	// Provided -- the deliverable main().
	// You can edit to do easier cases, but turn in
	// solving hardGrid.
	public static void main(String[] args) {
		Sudoku sudoku;
		sudoku = new Sudoku(hardGrid);
		System.out.println(sudoku); // print the raw problem
		int count = sudoku.solve();
		System.out.println("solutions:" + count);
		System.out.println("elapsed:" + sudoku.getElapsed() + "ms");
		System.out.println(sudoku.getSolutionText());

	}
	
	
	

	/**
	 * Sets up based on the given ints.
	 */
	public Sudoku(int[][] ints) {
		try{
		startingPuzzle = ints;
		puzzle = new Spot[ints.length][ints[0].length];
		puzzleInit(ints);
		solutionText = "";
		//FOR TESTS
//		spotSorted = new ArrayList<>(SIZE*SIZE);

		}catch (Exception e){
			throw new RuntimeException("Parsing problem");
		}
	}


	public Sudoku(String text){
		this(textToGrid(text));
	}


	private void puzzleInit(int[][] ints) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++){
				puzzle[i][j] = new Spot(i,j,ints[i][j]);
			}
		}
	}



	/**
	 * Solves the puzzle, invoking the underlying recursive search.
	 */
	public int solve() {
		//For tests
//		spotSorted = spotSort();
//		return 0;


		saveStartTime = System.currentTimeMillis();
		ArrayList<Spot> spotSorted = spotSort();

		int result = solveHelper(0,spotSorted);
		saveLastTime = System.currentTimeMillis();
		return result; // YOUR CODE HERE
	}

	private int solveHelper(int arrayIndex, ArrayList<Spot> spotSorted) {
		if(arrayIndex == spotSorted.size()) {
			if(solutionText.equals("")) solutionText = string(puzzle);
			return 1;
		}

		Spot choose = spotSorted.get(arrayIndex);

		//trySpotNumbersByCycle
		int result = 0;
		for(int i = 1 ; i <=9 ; i++) {
			if (proper(choose,i)) {
				choose.set(i);
				result += solveHelper(arrayIndex + 1, spotSorted);
				if(result >= MAX_SOLUTIONS) return MAX_SOLUTIONS;
				choose.set(0);
			}
		}


		return result;
	}


	private boolean proper(Spot spot, int i) {
		int row = -1;
		int squareXIndex = (spot.getCol()-1)/3 * PART;
		int squareYIndex = (spot.getRow()-1)/3 * PART;
		for(int j = 0 ; j < 9; j++) {
			if(puzzle[spot.getRow()-1][j].getNumber() == i) return false;
			if(puzzle[j][spot.getCol()-1].getNumber() == i) return false;
			if(j%3 == 0) row++;
			int col = j % 3;
			int x = squareXIndex + row;
			int y = squareYIndex + col;
			if(puzzle[squareYIndex + col][squareXIndex + row].getNumber() == i) return false;
		}
		return true;
	}

	private ArrayList<Spot> spotSort() {
		ArrayList<Spot> sorted = new ArrayList<>(SIZE*SIZE);
		int filledLength = 0;
		int indexForSpot = 0;
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				if(puzzle[i][j].getNumber() == 0) {
					indexForSpot = filledLength;
					for (int k = 1; k <= filledLength; k++) {
						if (compareTwoSpot(sorted.get(indexForSpot-1), puzzle[i][j])) {
							indexForSpot--;
						} else {
							break;
						}
					}
					filledLength++;
					sorted.add(indexForSpot, puzzle[i][j]);
				}
			}

		}

//		for(int i = 0 ; i < sorted.size(); i++){
//			System.out.println(i + " :  Row - " +sorted.get(i).getRow() + "  Col -  " + sorted.get(i).getCol());
//		}


		return sorted;
	}

	private boolean compareTwoSpot(Spot previusSpot, Spot newSpot) {
		int prevSpotAssignableNumber = findAssignableNumber(previusSpot);
		int newSpotAssignableNumber = findAssignableNumber(newSpot);
		return newSpotAssignableNumber < prevSpotAssignableNumber;
	}



	private int findAssignableNumber(Spot spot) {
		HashSet<Integer> neighbours = new HashSet<Integer>();

		for(int j = 0 ; j < 9; j++){
			neighbours.add(puzzle[spot.getRow()-1][j].getNumber());
			neighbours.add(puzzle[j][spot.getCol()-1].getNumber());
		}

		int squareXIndex = (spot.getCol()-1)/3 * PART;
		int squareYIndex = (spot.getRow()-1)/3 * PART;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				neighbours.add(puzzle[squareYIndex + j][squareXIndex + i].getNumber());
			}
		}


		int maxNumberOfNeighbours = 10; //neighbours must include 0, at least itself.getNumber is 0
		int result = maxNumberOfNeighbours-neighbours.size();

//	System.out.println("Row :  " + spot.getRow() + "  Col:  " + spot.getCol() + "  Ass Num:  " + result);

		return result;
	}

	public String getSolutionText() {
		return solutionText; // YOUR CODE HERE
	}

	public long getElapsed() {
		return saveLastTime - saveStartTime; // YOUR CODE HERE
	}

	@Override
	public String toString(){
		StringBuilder string = gridToString(startingPuzzle);
		return string.toString();
	}

	private StringBuilder gridToString(int[][] puzzle) {
		StringBuilder string = new StringBuilder();
		for(int i = 0; i < SIZE; i++) {
			if(i != 0) string.append('\n');
			for(int j = 0; j < SIZE; j++) {
				if(j != 0) string.append(" ");
				string.append(puzzle[i][j]);
			}
		}
		return string;
	}

	private String string(Spot[][] puzzle) {
		int[][] solved = puzzleToIntGrid(puzzle);
		StringBuilder solvedInString = gridToString(solved);
		return solvedInString.toString();
	}

	private int[][] puzzleToIntGrid(Spot[][] puzzle) {
		int[][] result = new int[puzzle.length][puzzle[0].length];
		for(int i = 0 ; i < puzzle.length; i++){
			for(int j = 0 ; j < puzzle[0].length; j++){
				result[i][j] = puzzle[i][j].getNumber();
			}
		}
		return result;
	}

}
