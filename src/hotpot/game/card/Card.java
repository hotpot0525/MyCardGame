package hotpot.game.card;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

//public class Card extends ImageButton {
public class Card extends Button {

    Button card;
    ChartView layout = null;
    int color;
    // 繧ｫ繝ｼ繝峨�荳贋ｸ句ｷｦ蜿ｳ縺ｮ謨ｰ蛟､蜷�    
    public static final String VALUE_LEFT = "cardValueLeft";
    public static final String VALUE_TOP = "cardValueTop";
    public static final String VALUE_Right = "cardValueRight";
    public static final String VALUE_Bottom = "cardValueBottom";

    boolean initFlag = false;
    public int defColor;
    // public int cardValue;
    HashMap<String, Integer> cardValue = new HashMap<String, Integer>();

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        TextView _tv = new TextView(getContext());
        _tv.setText("hogehoge");
        // TODO Auto-generated method stub
        super.onDraw(canvas);

    }

    // �ｽ�ｽ�ｽ�ｽﾊ置�ｽﾉ戻ゑｿｽ
    public void reset() {
    }

    /**
     * �ｽJ�ｽ[�ｽh�ｽ�ｽ�ｽ�ｽﾌ�ｿｽ�ｽC�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ
     * 
     * @param layout
     *            ... �ｽ{�ｽ^�ｽ�ｽ�ｽﾌ擾ｿｽﾊ�ｿｽ�ｽC�ｽA�ｽE�ｽg�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg
     * @param event
     *            ... �ｽw�ｽ�ｽ�ｽ
     * @param fields
     *            ... �ｽﾝ置�ｽ}�ｽX�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg�ｽﾌ配�ｽ�ｽ
     */
    public void onTouch(RelativeLayout layout, MotionEvent event, ArrayList<ChartView> fields, Game game) {

    }

    /**
     * �ｽw�ｽﾉゑｿｽ�ｽ�ｹ�ｽﾄ画像�ｽ�ｮゑｿｽ�ｽ�ｽ�ｽ�ｽ�ｽ�ｽ
     * 
     * @param event
     *            ... �ｽw�ｽﾌ位置�ｽ�ｽ�ｽ
     */
    public void onMoveToFingure(MotionEvent event) {
        // �ｽw�ｽﾌ位置�ｽ�ｽ�ｽJ�ｽ[�ｽh�ｽﾌ抵ｿｽ�ｽS�ｽﾉゑｿｽ�ｽ�ｽ
        int left = (int) event.getRawX() - getWidth() / 2;
        int right = (int) event.getRawX() + getWidth() / 2;
        int top = (int) event.getRawY() - getHeight() / 2 - 55;
        int bottom = (int) event.getRawY() + getHeight() / 2 - 55;

        layout(left, top, right, bottom);
    }

    /**
     * �ｽJ�ｽ[�ｽh�ｽ�ｽ�ｽ}�ｽX�ｽﾌ擾ｿｽﾉゑｿｽ�ｽ驍ｩ�ｽ`�ｽF�ｽb�ｽN
     * 
     * @return �ｽ�ｽ�ｽ�ｽﾎマ�ｽX�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg�ｽ�ｽﾔゑｿｽ�ｽB�ｽﾈゑｿｽ�ｽ�ｽ�ｽnull
     */
    public ChartView checkInField(ArrayList<ChartView> fields, MotionEvent event) {
        int E_X = (int) event.getRawX();
        int E_Y = (int) event.getRawY() - 55;

        for (int i = 0; i < fields.size(); i++) {
            ChartView field = fields.get(i);

            // �ｽ}�ｽX�ｽ�ｽ�ｽﾉ指�ｽ�ｽ�ｽ�ｽ�ｽ�ｽA�ｽﾝ置�ｽﾂ能�ｽﾅゑｿｽ�ｽ�ｽﾎ、�ｽ�ｽ�ｽ�ｽfield�ｽI�ｽu�ｽW�ｽF�ｽN�ｽg�ｽ�ｽﾔゑｿｽ
            if (field.getLeft() < E_X && E_X < field.getRight()) {
                if (field.getTop() < E_Y && E_Y < field.getBottom()) {
                    return field;
                }
            }
        }
        return null;
    }

    public void changeColor(int color) {
        setBackgroundColor(color);
    }

    public void setCardValue(int l, int t, int r, int b) {
        cardValue.put(VALUE_LEFT , l);
        cardValue.put(VALUE_TOP, t);
        cardValue.put(VALUE_Right, r);
        cardValue.put(VALUE_Bottom, b);
        // cardValue = value;
    }

}
