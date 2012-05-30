package hotpot.game.card;

import android.util.Log;

public class Hoge {

	public Huga huga = new Huga();
	
	public int hogeAction(){
		
		int result = huga.hugaAction();
		
		Log.i("HOGE","this is a hogeAction()");
		return result;
		
	}
	
}
