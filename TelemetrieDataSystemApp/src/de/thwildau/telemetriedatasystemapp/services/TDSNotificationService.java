package de.thwildau.telemetriedatasystemapp.services;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import de.thwildau.telemetriedatasystemapp.R;
import de.thwildau.telemetriedatasystemapp.ReceiveMessages;
import de.thwildau.telemetriedatasystemapp.connection.HTTPConnection;
import de.thwildau.telemetriedatasystemapp.test.TDSTest;

public class TDSNotificationService extends Service {

	private static NotificationManager notificationManager;
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();

		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		context = this;

		Log.v("OnCREATE=", "START BACKGROUND SERVICE");

		Timer timer = new Timer();
		timer.schedule(new MessageTask(), 10000, 10000);
	}

	@Override
	public void onDestroy() {
		Log.v("OnCREATE=", "STOP BACKGROUND SERVICE");
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
		CharSequence contentText = "From your observed car.";
		long when = System.currentTimeMillis();

		Intent intent = new Intent(context, ReceiveMessages.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,	intent, 0);
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

		notification.setLatestEventInfo(context, contentTitle, contentText,	contentIntent);

		notificationManager.notify(10001, notification);
	}

	class MessageTask extends TimerTask {
		@Override
		public void run() {
			HTTPConnection conn = new HTTPConnection();
			try {
				AsyncTask<String, Void, String> result = conn.execute("");
				if(result.get().contentEquals("new")){
					sendAndroidNotification(true);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
