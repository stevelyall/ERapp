package ca.stevenlyall.ersurvey;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReciever extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("alarm", "alarm Reciever recieves intent" + intent.toString());
		// intent to start the survey activity
		Intent startSurvey = new Intent(context, SurveyStartActivity.class);
		PendingIntent pendStartSurvey = PendingIntent.getActivity(context, 0,
				startSurvey, PendingIntent.FLAG_UPDATE_CURRENT);

		// notification
		Notification surveyTime = new Notification.Builder(context)
				.setContentTitle("Assessment time")
				.setContentText("Click to start survey")
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentIntent(pendStartSurvey).setAutoCancel(true)
				.addAction(R.drawable.abc_ic_search, "Go", pendStartSurvey)
				.build();

		NotificationManager nm = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);

		nm.notify(0, surveyTime);
	}

}
