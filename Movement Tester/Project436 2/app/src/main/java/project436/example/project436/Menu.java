package project436.example.project436;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClick(View view){
        Intent i = new Intent(this, TapStart.class);

        startActivity(i);
    }


    public void clickSpiral(View view) {
        Intent i = new Intent(this, SpiralStart.class);

        startActivity(i);
    }

    public void clickLevel(View view) {
        Intent i = new Intent(this, LevelStart.class);

        startActivity(i);
    }
    public void clickHead(View view) {
        Intent i = new Intent(this, HeadStart.class);

        startActivity(i);
    }
    public void clickArm(View view) {
        Intent i = new Intent(this, CurlStart.class);

        startActivity(i);
    }
    public void onResults(View view) {
        Intent i = new Intent(this, Results.class);

        startActivity(i);
    }

    public void reactionTime(View view) {
        Intent i = new Intent(this, ReactionStart.class);

        startActivity(i);
    }
}
