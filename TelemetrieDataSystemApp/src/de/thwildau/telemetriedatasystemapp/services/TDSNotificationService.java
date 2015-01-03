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

/**
 * Class defines a Background Service specially for Android
 * extends Service
 * @author Fabian
 *
 */
public class TDSNotificationService extends Service {

	//needed global values
	private static NotificationManager notificationManager;
	private static Context context;

	/**
	 * onCreate method, runs at service startup
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		//register service
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		context = this;

		Log.v("OnCREATE=", "START BACKGROUND SERVICE");

		//define timer and its interval 
		Timer timer = new Timer();
		timer.schedule(new MessageTask(), 0, 10000);
	}

	/**
	 * onDestroy method
	 * used for stopping service
	 */
	@Override
	public void onDestroy() {
		Log.v("OnCREATE=", "STOP BACKGROUND SERVICE");
		super.onDestroy();

	}

	/**
	 * onStartCommand method (predefined)
	 * not useed
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * onBind method (predefined)
	 * not used
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// http://www.javacodegeeks.com/2014/01/android-service-tutorial.html
	/**
	 * method to create Android Notifications 
	 * @param led - status of led that should be turn on or off
	 */
	@SuppressWarnings("deprecation")
	public static void sendAndroidNotification(boolean led) {
		//define notification
		int icon = R.drawable.ic_launcher;
		CharSequence text = "TDS";
		CharSequence contentTitle = "TDS - Incoming Message";
		CharSequence contentText = "From your observed car.";
		long when = System.currentTimeMillis();

		//define entry point in application
		Intent intent = new Intent(context, ReceiveMessages.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,	intent, 0);
		Notification notification = new Notification(icon, text, when);

		//define led,vibration, light
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
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, contentTitle, contentText,	contentIntent);
		//start notification
		notificationManager.notify(10001, notification);
	}

	/**
	 * Subclass defines a TimerTask
	 * is needed to execute the HTTP Connection to the Server every x seconds (see onCreate method above)
	 * @author Fabian
	 *
	 */
	class MessageTask extends TimerTask {
		/**
		 * run method will be execute when timer expires 
		 */
		@Override
		public void run() {
			HTTPConnection conn = new HTTPConnection();		//connection to server is called
			try {
				AsyncTask<String, Void, String> result = conn.execute("");
				if(result.get().contentEquals("new")){		//check if new messages are available
					sendAndroidNotification(true);			//send notification
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
