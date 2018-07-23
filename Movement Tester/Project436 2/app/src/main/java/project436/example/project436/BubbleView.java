package project436.example.project436;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;


public class BubbleView extends View {
    private int diameter;
    public int x;
    public int y;
    private ShapeDrawable bubble;
    public BubbleView(Context context) {
        super(context);
        createBubble();
    }
    private void createBubble() {
        x = 500;
        y = 700;
        diameter = 100;
        bubble = new ShapeDrawable(new OvalShape());
        bubble.setBounds(x, y, x + diameter, y + diameter);
        bubble.getPaint().setColor(0xff00cccc);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bubble.draw(canvas);
    }
    protected void move(float f, float g) {
        x = (int) (x - (f * 2));
        y = (int) (y + (g * 2));
        bubble.setBounds(x, y, x + diameter, y + diameter);
    }
}
