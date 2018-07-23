package project436.example.project436;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HeadSwayActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager manager;
    private HeadSwayView bubbleView;
    private Sensor accel;
    private TextView time;
    private double score;
    private CountDownTimer timer;
    private int red = 0;
    private int yellow =0;
    private int green = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bubbleView = new HeadSwayView(this);
        setContentView(bubbleView);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
        timer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                //time.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                setContentView(R.layout.activity_bubble);

                time = (TextView)findViewById(R.id.textView4);
                /*
                int x = 500;
                int y = 700;
                double x2 = Math.pow((x-bubbleView.x),2);
                double y2 = Math.pow((y-bubbleView.y),2);
                double sum = x2+y2;
                score = Math.sqrt(sum);*/
                //score determine by red + yellow + green
                score = yellow + red*2;
                time.setText("Your score is: " + score);
            }
        };

        timer.start();
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // don't do anything; we don't care
    }
    public void onSensorChanged(SensorEvent event) {
        bubbleView.move(event.values[0], event.values[1]);


        if((Math.abs(event.values[0])+ Math.abs(event.values[1]-9.81))<1){
            bubbleView.setColor(0x80ff00);
            //bubbleView.invalidate();
            green++;
        }else if((Math.abs(event.values[0])+Math.abs(event.values[1]-9.81))<3){
            bubbleView.setColor(0xffff00);
            //bubbleView.invalidate();
            yellow++;
        }else{
            bubbleView.setColor(0xff8000);
            //bubbleView.invalidate();
            red++;
        }
        bubbleView.invalidate();

    }
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }


}
