package tsturm18.pos.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static Game game;
    private TextView freeFieldsCount;
    private Button[][] fields = new Button[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.freeFieldsCount = findViewById(R.id.textViewFreeSpaces);
        addButtonsToArray();

        game = new Game(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        button.getId();
    }

    public void reset(View view) {
        game=new Game(this);
    }

    private void addButtonsToArray() {
        int resID;
        int currentButton=1;
        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 3; j++) {
                resID = getResources().getIdentifier("button_" + currentButton, "id", getPackageName());
                fields[i-1][j] = findViewById(resID);
                fields[i-1][j].setOnClickListener(this);
                currentButton++;
            }
        }
    }

    public void showToast(String msg) {
        Toast toast;
        toast = new Toast(this);
        toast.setText(msg);
        toast.show();
    }

    public void updateFreeFieldsCounter(int currentFreeFields) {
        freeFieldsCount.setText(currentFreeFields);
    }

    public void setSymbol(int row, int col) {
        fields[row-1][col-1].setText(game.getCurrentPlayerSymbol());
    }


}