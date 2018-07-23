package project436.example.project436;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;


public class HeadSwayView extends View {
    private int diameter;
    public int x;
    public int y;
    private ShapeDrawable bubble;
    public HeadSwayView(Context context) {
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
        /*
        x = 500;
        y = 700;
        diameter = 100;
        bubble = new ShapeDrawable(new RectShape());
        bubble.setBounds(x, y, x + diameter, y + diameter);
        bubble.getPaint().setColor(0xff00cccc);
         */
    }
    protected void setColor(int color){
        bubble.getPaint().setColor(color);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bubble.draw(canvas);
    }
    protected void move(float f, float g) {
        float n = g - (float)9.81;
        x = (int) (x - f);
        y = (int) (y + n);
        bubble.setBounds(x, y, x + diameter, y + diameter);
    }
}
