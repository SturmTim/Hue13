package tsturm18.pos.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static GameLogic gameLogic;
    private TextView emptyFields;
    public TextView currentPlayer;
    private Button[][] fields = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.emptyFields = findViewById(R.id.textViewFreeSpaces);
        emptyFields.setText("9");
        currentPlayer = findViewById(R.id.textViewPlayer);
        currentPlayer.setText("Player: 1");
        addButtonsToArray();

        gameLogic = new GameLogic(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonID = v.getResources().getResourceName(button.getId());
        String[] split = buttonID.split("_");
        int row = Integer.parseInt(split[1])-1;
        int col = Integer.parseInt(split[2])-1;
        gameLogic.doMove(row,col,buttonArrayToSymbolArray(fields));


    }
    @SuppressLint("SetTextI18n")
    public void reset(View view) {
        gameLogic = new GameLogic(this);

        resetField();
    }

    public void resetField(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j].setText(" ");
            }
        }
        currentPlayer.setText("Player: 1");
        updateEmptyFields(9);
    }

    private void addButtonsToArray() {
        int resID;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                resID = getResources().getIdentifier("button_" + i + "_" + j, "id", getPackageName());
                fields[i-1][j-1] = findViewById(resID);
                fields[i-1][j-1].setOnClickListener(this);
            }
        }
    }

    private Symbol[][] buttonArrayToSymbolArray(Button[][] buttons){
        Symbol[][] gameStatus = new Symbol[3][3];
        for (int i =0; i < 3;i++){
            for (int j = 0; j < 3;j++){
                System.out.println(i + " " + j);
                String buttonSymbol = buttons[i][j].getText().toString();
                if(buttonSymbol.equals(" ")){
                    gameStatus[i][j] = Symbol.EMPTY;
                }else{
                    gameStatus[i][j] = Symbol.valueOf(buttonSymbol.toUpperCase());
                }
            }
        }
        return gameStatus;
    }

    public void showToast(String msg) {
        Toast toast;
        toast = new Toast(this);
        toast.setText(msg);
        toast.show();
    }

    @SuppressLint("SetTextI18n")
    public void updateEmptyFields(int emptyFields) {
        this.emptyFields.setText(emptyFields + "");
    }

    public void setSymbol(int row, int col) {
        fields[row][col].setText(gameLogic.getCurrentPlayerSymbol());
    }


}