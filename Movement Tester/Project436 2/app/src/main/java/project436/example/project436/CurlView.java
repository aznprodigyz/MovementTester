package project436.example.project436;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Bzhang on 3/24/17.
 */

public class CurlView extends View {
    private int diameter;
    private int x;
    private int y;
    private ShapeDrawable bubble;
    private long starttime;
    public CurlView(Context context) {
        super(context);
        createBubble();

    }
    private void createBubble() {
        x = 0;
        y = 0;
        diameter = 2000;
        bubble = new ShapeDrawable(new RectShape());
        bubble.setBounds(x, y, x, y);
        bubble.getPaint().setColor(0xffff0000);
        starttime = System.currentTimeMillis();
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bubble.draw(canvas);
    }
    protected void move(float f, float g) {
        //x = (int) (x + f);
        y = (int) (y + g);
        bubble.setBounds(x, y, x + diameter, y+diameter);
        if(y>0) {
            long endtime = System.currentTimeMillis();
            float duration = (endtime-starttime);
            Toast.makeText(getContext(), "Score:" + duration  , Toast.LENGTH_SHORT).show();
        }
    }
}
