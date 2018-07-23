package project436.example.project436;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.util.Date;

public class Results extends AppCompatActivity {

    TextView output;
    String FILENAME = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        output = (TextView) findViewById(R.id.test);
        try {
            FileInputStream fin = openFileInput(FILENAME);
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            output.setText(temp);
            Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
        }
    }
}
