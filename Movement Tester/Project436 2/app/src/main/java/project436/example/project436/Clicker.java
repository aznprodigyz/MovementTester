package project436.example.project436;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class Clicker extends AppCompatActivity {

    TextView tvTime;
    Button bClick, bStart;

    String FILENAME = "mydata";

    //File internalStorageDir = getFilesDir();
    //File data = new File(internalStorageDir, "data.csv");
    //private String file = "mydata";

    CountDownTimer timer;
    int time = 10;

    int clicks = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        bClick = (Button) findViewById(R.id.bClick);
        bStart = (Button) findViewById(R.id.bStart);

        bStart.setEnabled(true);
        bClick.setEnabled(false);

        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                tvTime.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                time--;
                tvTime.setText("Time: " + time);
                bStart.setEnabled(true);
                bClick.setEnabled(false);

                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                String printedString = (String.valueOf(clicks) + " clicks on " + currentDateTimeString + ".");
                String temp="";

                //FileOutputStream fos;

                //try {
                ///    fos = new FileOutputStream(data);
                //    fos.write(printedString.getBytes());
                //    fos.close();
                //} catch (java.io.IOException e) {
                //   e.printStackTrace();
                //}


                try {
                    FileInputStream fin = openFileInput(FILENAME);
                    int c;

                    while( (c = fin.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                }
                catch(Exception e){
                }

                temp = temp + "\n" + printedString;

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(temp.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AlertDialog alertDialog = new AlertDialog.Builder(Clicker.this).create();
                alertDialog.setTitle("You got: ");
                alertDialog.setMessage(printedString);
                alertDialog.setCancelable(true);
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        };

        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicks++;
            }
        });

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                bStart.setEnabled(false);
                bClick.setEnabled(true);
                clicks = 0;
                time = 10;
                tvTime.setText("Time: " + time);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Clicker Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
