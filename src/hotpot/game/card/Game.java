package hotpot.game.card;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Color;
import android.util.Log;

public class Game {

    public static boolean GameFinished = false;

    public int gameCount;
    public int winsCount=0;

    Game() {
        gameCount = 9;

    }

    public int resetCount() {
        gameCount = 9;
        return gameCount;
    }
    
    /**
     * èüÇ¡ÇΩÇ©Ç«Ç§Ç©îªíËÇ∑ÇÈ
     * @return
     */
    public boolean isWin(ArrayList<ChartView> f){
    	int p=0;
    	int e=0;

    	for(int i=0;i<f.size();i++){
    		Log.i("COLOR", f.get(i).card.color+"");
    	  	if(f.get(i).card.color == Color.BLUE ){
    	  		Log.i("WIN", f.get(i).card.color+"");
        		p++;
        	}else{
        		Log.i("LOSE", f.get(i).card.color+"");
        		e++;
        	}
    	}
  
    	if(p>e){
    		return true;
    	}
    
    	return false;
    }
}
