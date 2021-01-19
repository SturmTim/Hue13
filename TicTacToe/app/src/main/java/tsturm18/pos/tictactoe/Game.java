package tsturm18.pos.tictactoe;

import java.util.Random;

public class Game {

    private final MainActivity mainActivity;
    private Symbol playerSymbol;
    private int emptyFields;
    private boolean gameFinished;

    public Game(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        this.emptyFields = 9;
        this.gameFinished = false;

        playerSymbol = Symbol.X;
    }

    public String getCurrentPlayerSymbol() {
        return playerSymbol.toString();
    }

    private boolean rowChecker(Symbol[][] fields, int row, Symbol current) {
        for (int i = 0; i <= 3; i++) {
            if (!fields[row][i].equals(current))
                break;
            else if (i == 2)
                return true;
        }
        return false;
    }

    private boolean columnChecker(Symbol[][] fields, int col, Symbol current) {
        for (int i = 0; i <= 3; i++) {
            if (!fields[i][col].equals(current))
                break;
            else if (i == 2)
                return true;
        }
        return false;
    }

    private boolean diagonalChecker(Symbol[][] fields, int row, int col, Symbol current) {
        if (row == col) {
            for (int i = 0; i < 3; i++) {
                if (!fields[i][i].equals(current))
                    break;
                else if (i == 2)
                    return true;
            }
        }
        return false;
    }

    private boolean reverseDiagonalChecker(Symbol[][] fields, int row, int col, Symbol current) {

        return false;
    }

    private void decreaseFreeFields() {
        this.emptyFields--;
    }

    public void doMove(int row, int col, Symbol[][] matrix){

    }

    public boolean gameOverChecker(Symbol[][] matrix, int row, int col, Symbol current) {
        return rowChecker(matrix, row, current)
                && columnChecker(matrix, col, current)
                && diagonalChecker(matrix, row, col, current)
                && reverseDiagonalChecker(matrix, row, col, current);
    }

    private boolean tieChecker() {
        
        return emptyFields == 0 && !gameFinished;
    }

}

