package ca.stevenlyall.ersurvey;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button testButton, startSvcButton, testNotifyButton;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    testButton = (Button)findViewById(R.id.testButton);
	    testButton.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		Intent startSurvey = new Intent(getBaseContext(),SurveyActivity.class);
	    		startActivity(startSurvey);
	    		finish();
	    	}
	    });
	    
	    startSvcButton = (Button)findViewById(R.id.startServiceButton);
	    startSvcButton.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		getBaseContext().startService(new Intent(getBaseContext(),SurveyService.class));
	    	    Log.d("Service","Setupactivity starting SurveyService");
	    	}
	    });
	    
	    testNotifyButton = (Button)findViewById(R.id.testNotifButton);
	    testNotifyButton.setOnClickListener(new OnClickListener() {
	    	@Override
	    	public void onClick(View v) {
	    		Toast.makeText(getBaseContext(),"Testing notification for survey",Toast.LENGTH_LONG).show();
	    		Intent surveyAlarmNotify = new Intent(getBaseContext(), AlarmReciever.class);
	    		PendingIntent pend = PendingIntent.getActivity(getApplicationContext(), 0, surveyAlarmNotify,PendingIntent.FLAG_UPDATE_CURRENT);
	    		getBaseContext().sendBroadcast(surveyAlarmNotify);
	    		Log.i("notification","sending alarm notify intent broadcast");
	    		
	    	}
	    });
	}

}
