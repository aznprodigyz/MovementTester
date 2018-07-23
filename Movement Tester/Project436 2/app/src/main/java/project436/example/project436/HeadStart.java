package project436.example.project436;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HeadStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_start);
    }

    public void onClick(View view) {
        final Intent i = new Intent(this, HeadSwayActivity.class);

        AlertDialog alertDialog = new AlertDialog.Builder(HeadStart.this).create();
        alertDialog.setTitle("Try not to sway for 10 seconds");
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
