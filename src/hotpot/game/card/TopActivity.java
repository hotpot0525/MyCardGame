package hotpot.game.card;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TopActivity extends Activity {

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.top_page);

        startButton = (Button) findViewById(R.id.start);

        startButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // クリック時の処理
                Intent intent = new Intent(TopActivity.this, MyCardGameActivity.class);
                startActivity(intent);
            }
        });

    }

}
