package ca.stevenlyall.ersurvey;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SurveyService extends Service {
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("service","SurveyService started");
		
		Intent surveyAlarmNotify = new Intent(this, AlarmReciever.class);
		PendingIntent notifySender = PendingIntent.getBroadcast(getApplicationContext(), 0, surveyAlarmNotify,PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager am = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
		am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 500, AlarmManager.INTERVAL_HOUR*2, notifySender);

		Log.i("service", am.toString());
		
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	
	@Override
	public void onDestroy() {
		Log.i("service","SurveyService onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
