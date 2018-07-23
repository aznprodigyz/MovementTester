package project436.example.project436;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class Reactions extends AppCompatActivity {

    int counter = 0;
    ImageButton button;
    Random rand;

    final int height = Resources.getSystem().getDisplayMetrics().heightPixels / 2;
    final int width = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
    double time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactions);

        button = (ImageButton) findViewById(R.id.button);
        time = (double) System.currentTimeMillis();
        balloonCheck();

        Toast.makeText(Reactions.this, "Start!", Toast.LENGTH_SHORT).show();
    }

    public void balloonCheck() {
        rand = new Random();
        button.setX(rand.nextInt(width));
        button.setY(rand.nextInt(height));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                if (counter < 10) {
                    //Toast.makeText(Reactions.this, "Current: " + counter, Toast.LENGTH_SHORT).show();
                    balloonCheck();
                } else if (counter == 10) {
                    double totalTime = ((double)System.currentTimeMillis()- time)/1000.0;
                    Toast.makeText(Reactions.this, "Done: " + totalTime + " seconds", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Reactions.this, "Test complete. Return to main menu to try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
