package tsturm18.pos.tictactoe;

public class GameLogic {

    private final MainActivity mainActivity;
    private Symbol playerSymbol;
    private int emptyFields;
    private boolean gameFinished;

    public GameLogic(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        this.emptyFields = 9;
        this.gameFinished = false;

        playerSymbol = Symbol.X;
    }

    public String getCurrentPlayerSymbol() {

        return playerSymbol.toString();
    }

    public void doMove(int row, int col, Symbol[][] gameStatus){

        if(!gameStatus[row][col].equals(Symbol.EMPTY)){
            mainActivity.showToast("Dieses Feld ist schon belegt, wähle ein anderes.");
            return;
        }else{
            gameStatus[row][col] = playerSymbol;
            mainActivity.setSymbol(row,col);
            mainActivity.updateEmptyFields(decreaseFreeFields());

        }
        if(gameOverChecker(gameStatus,row,col,playerSymbol)){
            mainActivity.showToast( mainActivity.currentPlayer.getText().toString() + " hat gewonnen");
            gameFinished=true;
            mainActivity.resetField();
        }else if (tieChecker()){
            mainActivity.showToast("Unentschieden");
            gameFinished=true;
            mainActivity.resetField();
        }
        if (playerSymbol.equals(Symbol.X))
        {
            playerSymbol = Symbol.O;
            mainActivity.currentPlayer.setText("Player: 2");
        }
        else{
            playerSymbol = Symbol.X;
            mainActivity.currentPlayer.setText("Player: 1");
        }
    }



    public int decreaseFreeFields() {
        this.emptyFields--;
        return emptyFields;
    }

    private boolean rowChecker(Symbol[][] fields, int row, Symbol current) {
        if(fields[row][0].equals(current) && fields[row][1].equals(current) && fields[row][2].equals(current)){
            return true;
        }
        return false;
    }

    private boolean columnChecker(Symbol[][] fields, int col, Symbol current) {
        if(fields[0][col].equals(current) && fields[1][col].equals(current) && fields[2][col].equals(current)){
            return true;
        }
        return false;
    }

    private boolean diagonalChecker(Symbol[][] fields, Symbol current) {

        if(fields[0][0].equals(current) && fields[1][1].equals(current) && fields[2][2].equals(current)){
            return true;
        }
        else if(fields[0][2].equals(current) && fields[1][1].equals(current) && fields[2][0].equals(current)){
            return true;
        }
        return false;
    }


    public boolean gameOverChecker(Symbol[][] matrix, int row, int col, Symbol current) {
        return rowChecker(matrix, row, current)
                || columnChecker(matrix, col, current)
                || diagonalChecker(matrix, current);
    }

    private boolean tieChecker() {
        return emptyFields == 0 && !gameFinished;
    }

}

