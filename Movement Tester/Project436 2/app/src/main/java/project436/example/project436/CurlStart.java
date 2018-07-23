package project436.example.project436;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurlStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curl_start);
    }
    public void onClick(View view) {
        Intent i = new Intent(this, Arms.class);

        startActivity(i);
    }
}
