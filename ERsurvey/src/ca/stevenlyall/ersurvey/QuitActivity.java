package ca.stevenlyall.ersurvey;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuitActivity extends Activity {
	
	Button quitButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_quit);
	    
	    // button quits app
	    quitButton = (Button) findViewById(R.id.startButton);
	    quitButton.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View v) {
				finish();
				
			}
		
	    });
	    
	    //if button isn't clicked in 5 seconds, quits activity
		new CountDownTimer(5000,1000) {
			public void onFinish() {
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
						
			}
		}.start();
	}

}
