package hotpot.game.card;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MyCardGameActivity extends Activity implements OnTouchListener {

    Game game;

    Button giveUp;

    EnemyCard card1;
    EnemyCard card2;
    EnemyCard card3;
    EnemyCard card4;
    EnemyCard card5;
    PlayerCard card6;
    PlayerCard card7;
    PlayerCard card8;
    PlayerCard card9;
    PlayerCard card10;

    public ArrayList<ChartView> fields;
    public ArrayList<EnemyCard> enemys;

    RelativeLayout layout;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("MyCardgame", "OnCreate Start...");

        game = new Game();

        giveUp = (Button) findViewById(R.id.giveup);
        giveUp.setOnTouchListener(this);

        
        
        card1 = (EnemyCard) findViewById(R.id.button1);
        card2 = (EnemyCard) findViewById(R.id.button2);
        card3 = (EnemyCard) findViewById(R.id.button3);
        card4 = (EnemyCard) findViewById(R.id.button4);
        card5 = (EnemyCard) findViewById(R.id.button5);
        card6 = (PlayerCard) findViewById(R.id.button6);
        card7 = (PlayerCard) findViewById(R.id.button7);
        card8 = (PlayerCard) findViewById(R.id.button8);
        card9 = (PlayerCard) findViewById(R.id.button9);
        card10 = (PlayerCard) findViewById(R.id.button10);
        enemys = new ArrayList<EnemyCard>();
        enemys.add(0, card1);
        enemys.add(1, card2);
        enemys.add(2, card3);
        enemys.add(3, card4);
        enemys.add(4, card5);
        

        fields = new ArrayList<ChartView>();
        fields.add((ChartView) findViewById(R.id.chartView1));
        fields.add((ChartView) findViewById(R.id.chartView2));
        fields.add((ChartView) findViewById(R.id.chartView3));
        fields.add((ChartView) findViewById(R.id.chartView4));
        fields.add((ChartView) findViewById(R.id.chartView5));
        fields.add((ChartView) findViewById(R.id.chartView6));
        fields.add((ChartView) findViewById(R.id.chartView7));
        fields.add((ChartView) findViewById(R.id.chartView8));
        fields.add((ChartView) findViewById(R.id.chartView9));

       fields.get(0).setNextNode(null, null, fields.get(1), fields.get(3));
        fields.get(1).setNextNode(fields.get(0), null, fields.get(2), fields.get(4));
        fields.get(2).setNextNode(fields.get(1), null, null, fields.get(5));

        fields.get(3).setNextNode(null, fields.get(0), fields.get(4), fields.get(6));
        fields.get(4).setNextNode(fields.get(3), fields.get(1), fields.get(5), fields.get(7));
        fields.get(5).setNextNode(fields.get(4), fields.get(2), null, fields.get(8));

        fields.get(6).setNextNode(null, fields.get(3), fields.get(7), null);
        fields.get(7).setNextNode(fields.get(6), fields.get(4), fields.get(8), null);
        fields.get(8).setNextNode(fields.get(7), fields.get(5), null, null);

        card1.setOnTouchListener(this);
        card2.setOnTouchListener(this);
        card3.setOnTouchListener(this);
        card4.setOnTouchListener(this);
        card5.setOnTouchListener(this);
        card6.setOnTouchListener(this);
        card7.setOnTouchListener(this);
        card8.setOnTouchListener(this);
        card9.setOnTouchListener(this);
        card10.setOnTouchListener(this);

        layout = (RelativeLayout) findViewById(R.id.relativeLayout1);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("ゲーム開始");
        alertDialogBuilder.setMessage("ルールを予想してがんばって勝ってください");
        alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    public boolean onTouch(View v, MotionEvent event) {

//        if (v.getClass() == EnemyCard.class) {
//            EnemyCard card = (EnemyCard) v;
//            if (card.layout == null) {
//                card.onTouch(layout, event, fields, game);
//            }
//        }

        if (v.getClass() == PlayerCard.class) {
            PlayerCard card = (PlayerCard) v;

            if (card.layout == null) {
                boolean result = card.onTouch2(layout, event, fields, game);

                if(result){



                	for(int i=0; i< enemys.size();i++){
                		Log.i("FOR ENEMY" ,"hoge");
                		if(enemys.get(i).layout == null){
                			
                			for(int j=0; j< fields.size(); j++){
                				if(fields.get(j).setableFlag == true){
                					enemys.get(i).setCard(fields.get(j));
                					enemys.get(i).attack(fields.get(j));
                					game.gameCount--;
                					
                					break;
                				}
                			}
                		}else{
                			continue;
                		}
                		break;
                	}
                }
                
            }
        }

        if (v == giveUp) {
            resetGame();
        }

        if (game.gameCount <= 0) {

            gameOver();

        }

        // TODO Auto-generated method stub
        return true;
    }

    public void resetGame() {
        card1.reset();
        card2.reset();
        card3.reset();
        card4.reset();
        card5.reset();
        card6.reset();
        card7.reset();
        card8.reset();
        card9.reset();
        card10.reset();

        fields.get(0).reset();
        fields.get(1).reset();
        fields.get(2).reset();
        fields.get(3).reset();
        fields.get(4).reset();
        fields.get(5).reset();
        fields.get(6).reset();
        fields.get(7).reset();
        fields.get(8).reset();

    }

    public void gameOver() {
    	
    	if(game.isWin(fields)){
    		game.winsCount++;
    	       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    	        alertDialogBuilder.setTitle("勝利！"+"(連勝数："+game.winsCount+")");
    	        alertDialogBuilder.setMessage("続けますか？");

    	        alertDialogBuilder.setNegativeButton("いいえ、結構です", new DialogInterface.OnClickListener() {				
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(MyCardGameActivity.this, TopActivity.class);
						startActivity(i);
					}
				});
    	        alertDialogBuilder.setPositiveButton("あ、はい", new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog, int which) {
    	                resetGame();
    	                game.resetCount();
    	            }
    	        });
    	        alertDialogBuilder.setCancelable(true);
    	        AlertDialog alertDialog = alertDialogBuilder.create();
    	        alertDialog.show();	
    	}else{
    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("ゲームオーバー");
            alertDialogBuilder.setMessage("あなたの負けです。出直しましょう");

            alertDialogBuilder.setNeutralButton("あ、はい", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MyCardGameActivity.this, TopActivity.class);
                    startActivity(i);
                }
            });
            alertDialogBuilder.setCancelable(true);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
    	
        
    //指定ミリ秒実行を止めるメソッド
    public synchronized void sleep(long msec)
      {	
      	try
      	{
      		wait(msec);
      	}catch(InterruptedException e){}
      }
}