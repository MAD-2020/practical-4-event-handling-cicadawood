package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */

    private static final String TAG = "Whack-A-Mole-2.0!";
    private static final int[] btnIDArray = {R.id.button0,R.id.button1,R.id.button2,R.id.button3,R.id.button4,
                                            R.id.button5,R.id.button6,R.id.button7,R.id.button8};
     /*  Stores the 9 buttons IDs here for those who wishes to use array to create all 9 buttons.
            You may use if you wish to change or remove to suit your codes.*/

    private Button[] buttonList = new Button[btnIDArray.length];



    //private Button btn0;
    //private Button btn1;
    //private Button btn2;
    //private Button btn3;
   // private Button btn4;
   // private Button btn5;
   // private Button btn6;
   // private Button btn7;
  //  private Button btn8;
      private Button selectedBtn;
      CountDownTimer timer;
      CountDownTimer readyTimer;
      TextView scoreAdvancedText;

      int advancedScore=0;



    private void readyTimer() {
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */

        readyTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(Main2Activity.this, "Get Ready in " + millisUntilFinished / 1000 + "seconds", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown!" + millisUntilFinished / 1000);

            }
            @Override
            public void onFinish() {
                Toast.makeText(Main2Activity.this, "GO!", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready Countdown Complete");
                readyTimer.cancel();
                placeMoleTimer();
            }
        };
        readyTimer.start();
    }
        private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        timer = new CountDownTimer(Long.MAX_VALUE,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setNewMole();
                Log.v(TAG, "New Mole Location!");
            }
            @Override
            public void onFinish() {
                timer.cancel();
            }
        };
        timer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scoreAdvancedText = findViewById(R.id.scoreAdvance);
        Intent intent = getIntent();
        advancedScore = intent.getIntExtra("score",0);
        scoreAdvancedText.setText(String.valueOf(advancedScore));
        Log.v(TAG, "Current User Score: " + String.valueOf(advancedScore));
        readyTimer();



        for(int i=0; i<btnIDArray.length;i++){
            final int index = i;
            buttonList[i]=(Button)findViewById(btnIDArray[i]);
            buttonList[i].setText("O");
            buttonList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doCheck(buttonList[index]);
                }
            });
        }
        //for(final int id : btnIDArray){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
       // }
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText()=="*"){
            advancedScore+=1;
            Log.v(TAG, "Hit, score added!");
        }
        else{
            advancedScore-=1;
            Log.v(TAG, "Missed, point deducted!");
        }
        scoreAdvancedText.setText(String.valueOf(advancedScore));
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        for (Button i :buttonList){
            i.setText("O");
        }
        buttonList[randomLocation].setText("*");
    }
}

