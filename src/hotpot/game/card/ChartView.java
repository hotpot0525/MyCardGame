package hotpot.game.card;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ChartView extends View {

    public boolean setableFlag;
    FieldData fieldData;
    Card card;

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setableFlag = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Rect r = new Rect(0, 0, getWidth() - 1, getHeight() - 1);

        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3);
        p.setColor(Color.GREEN);
        canvas.drawRect(r, p);
    }

    public void changeSetableFlag() {
        Log.i("CHANGE", setableFlag + "");
        if (setableFlag) {
            setableFlag = false;
        } else {
            setableFlag = true;
        }
    }

    public void setNextNode(ChartView l, ChartView u, ChartView r, ChartView d) {
        fieldData = new FieldData();
        fieldData.leftNode = l;
        fieldData.upNode = u;
        fieldData.rightNode = r;
        fieldData.downNode = d;

    }

    public void reset() {
        setableFlag = true;
        card = null;
    }

    public class FieldData {
        ChartView leftNode;
        ChartView upNode;
        ChartView rightNode;
        ChartView downNode;
    }
}
