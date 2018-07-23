package project436.example.project436;

/**
 * Created by Bzhang on 4/3/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class Arms extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private final float[] mRotationMatrix = new float[16];
    private final float orientationThreshold = 0.7f;
    private long lCurlTimes[];
    private long rCurlTimes[];
    private int trialsComplete = 0;
    public static final int numTrials = 6; // 3 trials per arm
    private boolean isFaceUp;
    private int curlCount;
    public static final int curlGoal = 10;
    private long startTime;
    TextView curlText;
    Button curlButton;

    private boolean sensorIsRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arms_activity);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mRotationMatrix[0] = 1;
        mRotationMatrix[4] = 1;
        mRotationMatrix[8] = 1;
        mRotationMatrix[12] = 1;

        lCurlTimes = new long[3];
        rCurlTimes = new long[3];
        curlText = (TextView) findViewById(R.id.curl_text);
        curlButton = (Button) findViewById(R.id.curling_start_button);
        this.setButtonTrialText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorIsRegistered) {
            this.startSensor();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorIsRegistered) {
            this.stopSensor();
        }
    }

    private void setButtonTrialText() {
        if (trialsComplete < numTrials) {
            curlButton.setText("Begin Trial");
        } else {
            curlButton.setText("View Results");
        }
    }

    private void startSensor() {
        // poll every second
        mSensorManager.registerListener(this, mSensor, 1000000);
    }

    private void stopSensor() {
        mSensorManager.unregisterListener(this);
    }


    public void curlButtonPress(View v) {
        if (trialsComplete < numTrials) {
            curlButton.setVisibility(View.GONE);
            isFaceUp = false;
            curlCount = 0;
            this.startSensor();
            sensorIsRegistered = true;
        } else {
            long[] lScores = {this.lCurlTimes[0], this.lCurlTimes[1], this.lCurlTimes[2]};
            long[] rScores = {this.rCurlTimes[0], this.rCurlTimes[1], this.rCurlTimes[2]};

        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
            if (isFaceUp && (mRotationMatrix[10] < -orientationThreshold)) {
                isFaceUp = false;
                curlCount++;
                curlText.setText(String.format(Locale.getDefault(), "%d", curlCount));
                if (curlCount % 2 == 0) {
                    curlText.setBackgroundColor(Color.argb(255, 0, 0, 255));
                } else {
                    curlText.setBackgroundColor(Color.argb(255, 0, 255, 0));
                }
            } else if (!isFaceUp && (mRotationMatrix[10] > orientationThreshold)) {
                isFaceUp = true;
                if (curlCount == 0) {
                    startTime = System.nanoTime();
                }
            }
            if (curlCount >= curlGoal) {
                this.stopSensor();
                sensorIsRegistered = false;
                long elapsedTime = System.nanoTime() - startTime;
                if (trialsComplete % 2 == 0) {
                    lCurlTimes[trialsComplete / 2] = elapsedTime;
                } else {
                    rCurlTimes[trialsComplete / 2] = elapsedTime;
                }
                trialsComplete++;
                curlCount = 0;
                curlButton.setVisibility(View.VISIBLE);
                this.setButtonTrialText();
                curlText.setText(String.format(Locale.getDefault(), "Score: %.4f", elapsedTime / 20000000000.0));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}