package project436.example.project436;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LevelStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_start);
    }

    public void onClick(View view) {
        final Intent i = new Intent(this, BubbleActivity.class);

        AlertDialog alertDialog = new AlertDialog.Builder(LevelStart.this).create();
        alertDialog.setTitle("Try to keep the ball from rolling off the screen for 10 seconds");
        alertDialog.setCancelable(true);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                startActivity(i);
            }
        });
        alertDialog.show();
    }

}
