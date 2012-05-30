package hotpot.game.card;

import hotpot.game.card.ChartView.FieldData;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PlayerCard extends Card {

    Button card;
    ChartView layout;
    Game game;

    boolean initFlag = false;
    int defLeft;
    int defRight;
    int defTop;
    int defBottom;
    public int defColor = Color.BLUE;

    public PlayerCard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (!initFlag) {
            init();
        }
        Paint p = new Paint();

        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GRAY);

        canvas.drawRect(3f, 3f, getWidth() - 3, getHeight() - 3, p);

        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        canvas.drawOval(new RectF(getWidth() / 2 - 10, -5f, getWidth() / 2 + 5, 10f), p);// 荳贋ｸｸ
        canvas.drawOval(new RectF(-5f, getHeight() / 2 - 10, 10f, getHeight() / 2 + 5), p);// 蟾ｦ荳ｸ
        canvas.drawOval(new RectF(getWidth() - 10, getHeight() / 2 - 10, getWidth() + 5, getHeight() / 2 + 5), p);// 蜿ｳ荳ｸ
        canvas.drawOval(new RectF(getWidth() / 2 - 10, getHeight() - 10, getWidth() / 2 + 5, getHeight() + 5), p);// 荳倶ｸｸ

        p.setTextSize(12);
        p.setColor(Color.BLACK);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.getFontMetrics();

        canvas.drawText(cardValue.get(VALUE_TOP).toString(), getWidth() / 2 - 5, 10, p);
        canvas.drawText(cardValue.get(VALUE_LEFT).toString(), 1f, getHeight() / 2 + 2, p);
        canvas.drawText(cardValue.get(VALUE_Right).toString(), getWidth() - 6, getHeight() / 2 + 2, p);
        canvas.drawText(cardValue.get(VALUE_Bottom).toString(), getWidth() / 2 - 5, getHeight() - 1, p);
    }

    public void init() {
        defLeft = getLeft();
        defRight = getRight();
        defTop = getTop();
        defBottom = getBottom();

        defColor = Color.BLUE;
        setBackgroundColor(Color.BLUE);

        // 繧ｫ繝ｼ繝画焚蛟､縺ｮ繧ｻ繝�ヨ
        cardValue.put(VALUE_LEFT, (int) Math.floor(Math.random() * 10));
        cardValue.put(VALUE_TOP, (int) Math.floor(Math.random() * 10));
        cardValue.put(VALUE_Right, (int) Math.floor(Math.random() * 10));
        cardValue.put(VALUE_Bottom, (int) Math.floor(Math.random() * 10));

        // setCardValue(20);
        // setImageResource(R.drawable.button_50);

        initFlag = true;
    }

    @Override
    public void reset() {
        setBackgroundColor(defColor);
        layout(defLeft, defTop, defRight, defBottom);

        layout = null;
    }


    public boolean onTouch2(RelativeLayout layout, MotionEvent event, ArrayList<ChartView> fields, Game game) {

        if (this.game == null) {
            this.game = game;
        }

        int action = event.getAction();
        layout.bringChildToFront(this);

        if (action == MotionEvent.ACTION_MOVE) {
            onMoveToFingure(event);
        } else if (action == MotionEvent.ACTION_DOWN) {
            Log.i("ACTION", "ACTION_DOWN");
            ChartView field = checkInField(fields, event);
            Log.i("filed", field + "");

            if (field != null) {
                field.changeSetableFlag();
                field.card = null;
            }
            onMoveToFingure(event);
        } else if (action == MotionEvent.ACTION_UP) {
            ChartView field = checkInField(fields, event);
            if (field != null && field.setableFlag) {
                boolean result = setCard(field);
                Log.i("HOGEHOGE", result+"");
                attack(field);
                return result;

            } else {
                reset();
            }
        }
        return false;
    }


    @Override
    public void onMoveToFingure(MotionEvent event) {
        int left = (int) event.getRawX() - getWidth() / 2;
        int right = (int) event.getRawX() + getWidth() / 2;
        int top = (int) event.getRawY() - getHeight() / 2 - 55;
        int bottom = (int) event.getRawY() + getHeight() / 2 - 55;

        layout(left, top, right, bottom);
    }

 
    @Override
    public ChartView checkInField(ArrayList<ChartView> fields, MotionEvent event) {
        int E_X = (int) event.getRawX();
        int E_Y = (int) event.getRawY() - 55;

        for (int i = 0; i < fields.size(); i++) {
            ChartView field = fields.get(i);

            if (field.getLeft() < E_X && E_X < field.getRight()) {
                if (field.getTop() < E_Y && E_Y < field.getBottom()) {
                    return field;
                }
            }
        }
        return null;
    }

    public boolean setCard(ChartView cv) {
        int left = cv.getLeft();
        int right = cv.getRight();
        int top = cv.getTop();
        int bottom = cv.getBottom();
        setBackgroundColor(defColor);
        layout(left, top, right, bottom);
        cv.changeSetableFlag();
        cv.card = this;
        game.gameCount--;
        layout = cv;

        return true;
    }

    @Override
    public void changeColor(int color) {
        setBackgroundColor(color);
    }

 
    public void attack(ChartView field) {
    	FieldData fd = field.fieldData;
        if (fd.upNode != null && fd.upNode.card != null && fd.upNode.card.cardValue.get(VALUE_Bottom) < cardValue.get(VALUE_TOP)) {
            Log.i("ENEMY_DATA", fd.upNode.card.cardValue + "");
            Log.i("ENEMY_DATA", cardValue + "");
            fd.upNode.card.changeColor(Color.BLUE);
        }

        if (fd.leftNode != null && fd.leftNode.card != null
                && fd.leftNode.card.cardValue.get(VALUE_Right) < cardValue.get(VALUE_LEFT)) {
            fd.leftNode.card.changeColor(Color.BLUE);
        }

        if (fd.rightNode != null && fd.rightNode.card != null
                && fd.rightNode.card.cardValue.get(VALUE_LEFT) < cardValue.get(VALUE_Right)) {
            fd.rightNode.card.changeColor(Color.BLUE);
        }

        if (fd.downNode != null && fd.downNode.card != null
                && fd.downNode.card.cardValue.get(VALUE_TOP) < cardValue.get(VALUE_Bottom)) {
            fd.downNode.card.changeColor(Color.BLUE);
        }

    }
    
    @Override
    public void setBackgroundColor(int color) {
    	super.setBackgroundColor(color);
    	super.color = color;
    }
}
