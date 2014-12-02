package de.thwildau.telemetriedatasystemapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

public class TDSNotificationService extends Service {

	private static NotificationManager notificationManager;
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();

		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		context = this;
		
		Log.v("OnCREATE=","TEST SERVICE");
		//initiate loop for requesting server
		//prepare data
		//set data like test Method
		TDSTest test = new TDSTest();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// http://www.javacodegeeks.com/2014/01/android-service-tutorial.html
	public static void sendAndroidNotification(boolean led) {
		int icon = R.drawable.ic_launcher;
		CharSequence text = "TDS";
		CharSequence contentTitle = "TDS - Incoming Message";
		CharSequence contentText = "Something happend with your observed car.";
		long when = System.currentTimeMillis();

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		Notification notification = new Notification(icon, text, when);

		long[] vibrate = { 0, 100, 200, 300 };
		notification.vibrate = vibrate;

		notification.ledARGB = Color.RED;
		notification.ledOffMS = 300;
		notification.ledOnMS = 300;

		if (!(led)) {
			notification.defaults |= Notification.DEFAULT_LIGHTS;
		} else {
			notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		}

		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);

		notificationManager.notify(10001, notification);
	}

}
