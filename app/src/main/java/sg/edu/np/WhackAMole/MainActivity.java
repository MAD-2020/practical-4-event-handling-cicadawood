package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* Hint
        - ✔ The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    private Button leftBtn;
    private Button middleBtn;
    private Button rightBtn;
    private TextView scoreNormalText;
    private int score = 0;
    private Button btnSelected;

    private static final String TAG = "Whack-A-Mole-1.0!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Finished Pre-Initialisation!");
        leftBtn = findViewById(R.id.leftButton);
        middleBtn = findViewById(R.id.middleButton);
        rightBtn = findViewById(R.id.rightButton);
        scoreNormalText = findViewById(R.id.scoreNormal);

        leftBtn.setOnClickListener(this);
        middleBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        scoreNormalText.setText("0");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftButton:
                btnSelected = findViewById(R.id.leftButton);
                break;
            case R.id.middleButton:
                btnSelected = findViewById(R.id.middleButton);
                break;
            case R.id.rightButton:
                btnSelected = findViewById(R.id.rightButton);
                break;
        };

        doCheck(btnSelected);
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
        String logMsg;

        switch(checkButton.getId()){
            case R.id.leftButton:
                if (leftBtn.getText().toString().equals("*")){
                    score += 1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg = "Hit, score added!";
                }
                else{
                    score-=1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg= "Missed, score deducted!";
                }
                Log.v(TAG,"Button Left Clicked!\n"+logMsg);
                break;

            case R.id.middleButton:

                if (middleBtn.getText().toString().equals("*")){
                    score += 1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg = "Hit, score added!";
                }
                else{
                    score-=1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg= "Missed, score deducted!";
                }
                Log.v(TAG,"Button Middle Clicked!\n"+logMsg);
                break;

            case R.id.rightButton:

                if (rightBtn.getText().toString().equals("*")){
                    score += 1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg = "Hit, score added!";
                }
                else{
                    score-=1;
                    setNewMole();
                    scoreNormalText.setText(String.valueOf(score));
                    logMsg= "Missed, score deducted!";
                }
                Log.v(TAG,"Button Right Clicked!\n"+logMsg);
                break;
        }
        if (score%10==0){
            nextLevelQuery();
        }
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        Log.d(TAG,"Advance option given to user!");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Waring! Insane Whack-A-Mole incoming!");
        builder.setMessage("Would you like to advance to advances mode?");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nextLevel();
                Log.d(TAG,"User accepts!");
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG,"User decline!");

            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if (randomLocation ==1){
            leftBtn.setText("*");
            middleBtn.setText("O");
            rightBtn.setText("O");
        }
        else if (randomLocation ==2){
            leftBtn.setText("O");
            middleBtn.setText("*");
            rightBtn.setText("O");
        }
        else{
            leftBtn.setText("O");
            middleBtn.setText("O");
            rightBtn.setText("*");
        }
    }


}