package project436.example.project436;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SpiralStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiral_start);
    }
    public void onClick(View view) {
        Intent i = new Intent(this, Spiral.class);

        startActivity(i);
    }
}
