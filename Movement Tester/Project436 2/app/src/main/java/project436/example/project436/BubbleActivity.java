package project436.example.project436;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BubbleActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager manager;
    private BubbleView bubbleView;
    private Sensor accel;
    private TextView time;
    private double score;
    private CountDownTimer timer;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bubbleView = new BubbleView(this);
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
                int x = 500;
                int y = 700;
                double x2 = Math.pow((x-bubbleView.x),2);
                double y2 = Math.pow((y-bubbleView.y),2);
                double sum = x2+y2;
                score = Math.sqrt(sum);
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
