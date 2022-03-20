


import org.junit.Test;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

//    !!!!!
//    There is too many commented tests. they are not customized on
//    final program. I used them in writing process, step by step
//    so if you want to try program this tests you will need
//    some changes in Sudoku.java. Every test has necessary changes
//    top of them. At bottom there is tests customized at final
//    version of program.



//
//    //needs puzzle grid and Spot methods to be public
//    @Test
//    public void testSimple1() {
//         int[][] grid = Sudoku.stringsToGrid(
//                "0 0 0 0 0 0 0 0 0",
//                "5 0 0 6 4 4 9 1 0",
//                "0 0 5 0 9 0 4 0 7",
//                "0 9 0 0 9 6 5 0 0",
//                "5 2 0 1 4 2 0 0 8",
//                "0 0 8 9 0 0 0 3 0",
//                "8 0 9 3 4 0 7 0 0",
//                "0 7 3 5 0 9 0 5 1",
//                "4 0 3 3 0 0 6 7 9");
//
//         Sudoku sudoku = new Sudoku(grid);
//
//         assertEquals(sudoku.puzzle[4][3].getRow(),5);
//        assertEquals(sudoku.puzzle[5][2].getRow(),6);
//        assertEquals(sudoku.puzzle[8][8].getNumber(),9);
//        assertEquals(sudoku.puzzle[3][6].getCol(),7);
//        assertEquals(sudoku.puzzle[0][0].getNumber(),0);
//    }
//
//
//    //secondary constructor test
//    //needs puzzle grid and Spot methods to be public
//    @Test
//    public void testSimple2(){
//        String string = "0 0 0 0 0 0 0 0 0 " +
//                "5 0 0 6 4 4 9 1 0 " +
//                "0 0 5 0 9 0 4 0 7 " +
//                "0 9 0 0 9 6 5 0 0 " +
//                "5 2 0 1 4 2 0 0 8 " +
//                "0 0 8 9 0 0 0 3 0 " +
//                "8 0 9 3 4 0 7 0 0 " +
//                "0 7 3 5 0 9 0 5 1 " +
//                "4 0 3 3 0 0 6 7 9";
//
//        Sudoku sudoku = new Sudoku(string);
//
//        assertEquals(sudoku.puzzle[4][3].getRow(),5);
//        assertEquals(sudoku.puzzle[5][2].getRow(),6);
//        assertEquals(sudoku.puzzle[8][8].getNumber(),9);
//        assertEquals(sudoku.puzzle[3][6].getCol(),7);
//        assertEquals(sudoku.puzzle[0][0].getNumber(),0);
//    }

    //Filled sudoku example
//            "4 3 5 2 6 9 7 8 1",
//            "6 8 2 5 7 1 4 9 3",
//            "1 9 7 8 3 4 5 6 2",
//            "8 2 6 1 9 5 3 4 7",
//            "3 7 4 6 8 2 9 1 5",
//            "9 5 1 7 4 3 6 2 8",
//            "5 1 9 3 2 6 8 7 4",
//            "2 4 8 9 5 7 1 3 6",
//            "7 6 3 4 1 8 2 5 9"



    //spotSorted Array needs to be public for tests
//    @Test
//    public void testSimple3(){
//         int[][] grid = Sudoku.stringsToGrid(
//                "4 3 5 2 6 9 7 8 1",
//                "6 8 2 5 7 1 4 9 0",
//                "1 9 7 8 3 4 5 6 2",
//                "8 2 6 1 9 5 3 4 7",
//                "3 7 4 6 8 2 9 1 5",
//                "9 5 1 7 4 3 6 2 8",
//                "5 1 9 3 2 6 8 7 4",
//                "2 4 8 9 5 7 1 0 6",
//                "7 6 0 4 1 8 2 5 0");
//
//         //Right Down most has only 2 option of number. others have only 1
//         Sudoku sudoku = new Sudoku(grid);
//
//        sudoku.solve();
//
//        assertEquals(sudoku.spotSorted.get(3).getCol(),9);
//        assertEquals(sudoku.spotSorted.get(3).getRow(),9);
//        assertEquals(sudoku.spotSorted.get(3).getNumber(),0);
//        assertEquals(sudoku.spotSorted.get(0).getCol(),9);
//        assertEquals(sudoku.spotSorted.get(0).getRow(),2);
//        assertTrue(sudoku.spotSorted.size() == 4);
//    }

    //findAssignableNumbers need to be public and puzzle
//    @Test
//    public void testSimple4(){
//        int[][] grid = Sudoku.stringsToGrid(
//                "0 1 4 5 0 0 0 0 0",
//                "1 2 3 0 0 0 0 0 0",
//                "5 0 0 0 0 0 0 0 0",
//                "6 0 0 0 0 0 0 0 0",
//                "0 0 0 0 0 0 0 0 0",
//                "0 0 0 0 0 0 0 0 0",
//                "0 0 0 0 0 0 0 0 0",
//                "0 0 0 0 0 0 0 0 0",
//                "0 0 0 0 0 0 0 0 0");
//
//        Sudoku sudoku = new Sudoku(grid);
//
//        assertEquals(sudoku.findAssignableNumber(sudoku.puzzle[8][8]),9);
//        assertEquals(sudoku.findAssignableNumber(sudoku.puzzle[0][0]),3);
//    }

    //same requairements as testSimple3
//    @Test
//    public void testCompicatedForSort(){
//        int[][] grid = Sudoku.stringsToGrid(
//                "0 0 5 2 6 9 7 8 1",
//                "6 8 2 5 7 1 4 9 0",
//                "1 9 7 8 3 0 5 6 2",
//                "8 2 0 0 9 5 0 4 7",
//                "0 7 4 6 0 2 9 1 5",
//                "9 5 0 7 4 3 6 2 8",
//                "5 1 9 0 2 6 0 7 4",
//                "2 4 8 9 5 7 1 0 6",
//                "7 6 0 4 1 8 2 5 0");
//
//        //Right Down most has only 2 option of number. others have only 1
//        Sudoku sudoku = new Sudoku(grid);
//
//        sudoku.solve();
//
//        assertTrue(sudoku.spotSorted.size() == 15);//number of zeros

//        int[][] gridSec = Sudoku.stringsToGrid(
//                "4 3 5 2 6 9 7 8 1",
//                "6 8 2 5 7 1 4 9 0",
//                "1 9 7 8 3 4 5 6 2",
//                "8 2 6 1 9 5 3 4 7",
//                "3 7 4 6 8 2 9 1 0",
//                "9 5 1 7 4 3 6 2 8",
//                "5 1 9 3 2 6 8 7 4",
//                "2 4 8 9 5 7 1 0 6",
//                "7 6 0 4 1 8 2 0 0");    // 3, 5, 9
//
//        Sudoku sudokuSec = new Sudoku(gridSec);
//
//        sudokuSec.solve();
//
//        assertEquals(sudokuSec.spotSorted.get(5).getCol(),9);
//        assertEquals(sudokuSec.spotSorted.get(5).getRow(),9);
//
//        assertEquals(sudokuSec.spotSorted.get(4).getCol(),8);
//        assertEquals(sudokuSec.spotSorted.get(4).getRow(),9);
//
//        assertEquals(sudokuSec.spotSorted.get(0).getCol(),9);
//        assertEquals(sudokuSec.spotSorted.get(0).getRow(),2);
//
//        assertEquals(sudokuSec.spotSorted.size(),6);
//    }

//    @Test
//    public void testSimple5(){
//        int[][] grid = Sudoku.stringsToGrid(
//    "4 3 5 2 6 9 7 8 0",
//            "6 8 2 5 7 1 4 9 0",
//            "1 9 7 8 3 4 5 6 2",
//            "8 2 6 1 9 5 3 4 7",
//            "3 7 4 6 0 2 9 1 5",
//            "9 5 1 7 0 3 6 2 8",     //5
//            "5 1 9 3 0 6 8 7 4",
//            "0 4 8 9 5 7 1 3 6",     //2
//            "7 6 3 4 1 8 2 5 9");
//
//        Sudoku sudoku = new Sudoku(grid);
//
//
//        assertEquals(sudoku.solve(), 1);
//        System.out.println(sudoku.getSolutionText());
//        System.out.println("Time:  " + sudoku.getElapsed());
//    }

    @Test
    public void testTwoSolutioSudocu(){
        int[][] grid = Sudoku.stringsToGrid(
                "4 3 5 2 6 9 0 0 1",    // 7 8 or 8 7
            "6 8 2 5 7 1 4 9 3",
            "1 9 7 8 3 4 5 6 2",
            "8 2 6 1 9 5 3 4 7",
            "3 7 4 6 8 2 9 1 5",
            "9 5 1 7 4 3 6 2 8",
            "5 1 9 3 2 6 0 0 4",               // 8 7 or 7 8
            "2 4 8 9 5 7 1 3 6",
            "7 6 3 4 1 8 2 5 9");

        Sudoku sudoku = new Sudoku(grid);

        long timeStart = System.currentTimeMillis();
        int i = sudoku.solve();
        assertEquals(System.currentTimeMillis()-timeStart,sudoku.getElapsed());
        assertEquals(2,sudoku.solve());
    }

    @Test
    public void testSudoku1(){
        int[][] grid = Sudoku.stringsToGrid(
                "2 0 0 3 0 0 0 0 0",
                "8 0 4 0 6 2 0 0 3",
                "0 1 3 8 0 0 2 0 0",
                "0 0 0 0 2 0 3 9 0",
                "5 0 7 0 0 0 6 2 1",
                "0 3 2 0 0 6 0 0 0",
                "0 2 0 0 0 9 1 4 0",
                "6 0 1 2 5 0 8 0 9",
                "0 0 0 0 0 1 0 0 2");

        int[][] answers = Sudoku.stringsToGrid(
                "2 7 6 3 1 4 9 5 8",
                "8 5 4 9 6 2 7 1 3",
                "9 1 3 8 7 5 2 6 4",
                "4 6 8 1 2 7 3 9 5",
                "5 9 7 4 3 8 6 2 1",
                "1 3 2 5 9 6 4 8 7",
                "3 2 5 7 8 9 1 4 6",
                "6 4 1 2 5 3 8 7 9",
                "7 8 9 6 4 1 5 3 2");

        String answerString = gridToString(answers).toString();

        Sudoku sudoku = new Sudoku(grid);

        long timeStart = System.currentTimeMillis();
        int i = sudoku.solve();
        assertEquals(System.currentTimeMillis()-timeStart,sudoku.getElapsed());
        assertEquals(1,i);
        assertEquals(answerString,sudoku.getSolutionText());
    }

    @Test
    public void testSudoku2(){
        int[][] grid = Sudoku.stringsToGrid(
                "3 0 0 0 0 0 0 8 0",
                "0 0 1 0 9 3 0 0 0",
                "0 4 0 7 8 0 0 0 3",
                "0 9 3 8 0 0 0 1 2",
                "0 0 0 0 4 0 0 0 0",
                "5 2 0 0 0 6 7 9 0",
                "6 0 0 0 2 1 0 4 0",
                "0 0 0 5 3 0 9 0 0",
                "0 3 0 0 0 0 0 5 1");

        Sudoku sudoku = new Sudoku(grid);

        long timeStart = System.currentTimeMillis();
        int i = sudoku.solve();
        assertEquals(System.currentTimeMillis()-timeStart,sudoku.getElapsed());
        assertEquals(6,i);
    }

    @Test
    public void testSudoku3(){
        int[][] grid = Sudoku.stringsToGrid(
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0",
                "0 0 0 0 0 0 0 0 0");

        Sudoku sudoku = new Sudoku(grid);

        int i = sudoku.solve();
        assertEquals(100,i);
    }

    @Test
    public void testSudoku4(){
        int[][] grid = Sudoku.stringsToGrid(
                "0 0 0 6 0 0 4 0 0",
                "7 0 0 0 0 3 6 0 0",
                "0 0 0 0 9 1 0 8 0",
                "0 0 0 0 0 0 0 0 0",
                "0 5 0 1 8 0 0 0 3",
                "0 0 0 3 0 6 0 4 5",
                "0 4 0 2 0 0 0 6 0",
                "9 0 3 0 0 0 0 0 0",
                "0 2 0 0 0 0 1 0 0");

        int[][] gridAns = Sudoku.stringsToGrid(
                "5 8 1 6 7 2 4 3 9",
                "7 9 2 8 4 3 6 5 1",
                "3 6 4 5 9 1 7 8 2",
                "4 3 8 9 5 7 2 1 6",
                "2 5 6 1 8 4 9 7 3",
                "1 7 9 3 2 6 8 4 5",
                "8 4 5 2 1 9 3 6 7",
                "9 1 3 7 6 8 5 2 4",
                "6 2 7 4 3 5 1 9 8");

        String gridAnsInString = gridToString(gridAns).toString();


        String gridText = gridToString(grid).toString();

        Sudoku sudoku = new Sudoku(gridText);

        int solveN = sudoku.solve();
        assertEquals(gridAnsInString, sudoku.getSolutionText());
        assertEquals(1,solveN);

    }

    private StringBuilder gridToString(int[][] puzzle) {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            if(i != 0) string.append('\n');
            for(int j = 0; j < 9; j++) {
                if(j != 0) string.append(" ");
                string.append(puzzle[i][j]);
            }
        }
        return string;
    }
    }