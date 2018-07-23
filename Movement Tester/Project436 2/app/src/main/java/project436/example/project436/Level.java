package project436.example.project436;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Level extends Activity {

    private GyroVisualizer mGyroView;
    private SensorManager mSensorManager;
    private Sensor mGyroSensor, mAccSensor, mMagSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mGyroView = new GyroVisualizer(this);
        setContentView(mGyroView);
        setContentView(R.layout.bubble_background);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mGyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    private SensorEventListener mGyroListener = new SensorEventListener() {

        private static final float MIN_TIME_STEP = (1f / 40f);
        private long mLastTime = System.currentTimeMillis();
        private float mRotationX, mRotationY, mRotationZ;

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float angularVelocity = z * 0.96f; // Minor adjustment to avoid drift on Nexus S

            // Calculate time diff
            long now = System.currentTimeMillis();
            float timeDiff = (now - mLastTime) / 1000f;
            mLastTime = now;
            if (timeDiff > 1) {
                // Make sure we don't go bananas after pause/resume
                timeDiff = MIN_TIME_STEP;
            }

            mRotationX += x * timeDiff;
            if (mRotationX > 0.5f)
                mRotationX = 0.5f;
            else if (mRotationX < -0.5f)
                mRotationX = -0.5f;

            mRotationY += y * timeDiff;
            if (mRotationY > 0.5f)
                mRotationY = 0.5f;
            else if (mRotationY < -0.5f)
                mRotationY = -0.5f;

            mRotationZ += angularVelocity * timeDiff;

            mGyroView.setGyroRotation(mRotationX, mRotationY, mRotationZ);
        }
    };

    private SensorEventListener mAccListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            // float z = values[2];

            // Ignoring orientation since the activity is using screenOrientation "nosensor"

            mGyroView.setAcceleration(-x, y);
        }
    };

    private SensorEventListener mMagListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            // float z = values[2];

            mGyroView.setMagneticField(x, -y);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mGyroListener, mGyroSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mAccListener, mAccSensor, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mMagListener, mMagSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mGyroListener, mGyroSensor);
        mSensorManager.unregisterListener(mAccListener, mGyroSensor);
        mSensorManager.unregisterListener(mMagListener, mMagSensor);
    }
}
