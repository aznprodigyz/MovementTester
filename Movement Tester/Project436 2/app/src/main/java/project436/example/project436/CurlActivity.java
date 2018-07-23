package project436.example.project436;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CurlActivity extends AppCompatActivity implements SensorEventListener {


    private SensorManager manager;
    private CurlView curlView;
    private Sensor accel;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curlView = new CurlView(this);
        curlView.setBackgroundColor(0xff00ff00);
        setContentView(curlView);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accel = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accel,
                SensorManager.SENSOR_DELAY_GAME);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // don't do anything; we don't care
    }
    public void onSensorChanged(SensorEvent event) {
        curlView.move(event.values[0], event.values[1]);
        curlView.invalidate();
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
