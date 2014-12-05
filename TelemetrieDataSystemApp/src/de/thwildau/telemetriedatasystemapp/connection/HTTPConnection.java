package de.thwildau.telemetriedatasystemapp.connection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import de.thwildau.telemetriedatasystemapp.data.ConnectionData;
import de.thwildau.telemetriedatasystemapp.data.MessageManager;
import de.thwildau.telemetriedatasystemapp.data.NotificationTypeManager;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;

public class HTTPConnection extends AsyncTask<String, Void, String> {

	private final String USER_AGENT = "Mozilla/5.0";
	NotificationTypeManager notificationMnmgr = NotificationTypeManager
			.getInstance();
	MessageManager msgMnmgr = MessageManager.getInstance();

	@Override
	protected String doInBackground(String... arg0) {
		Boolean result = false;

		try {
			String response = sendGet(defineRequestURL());
			getMessagesFromString(response);
			result = checkNewMessage();
			if (result == true) {
				return "new";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "not";
		}
		return "not";
	}

	// HTTP GET request for data
	private String sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		Log.v("Sending 'GET' request to URL", url);
		Log.v("ResponseCode", String.valueOf(responseCode));

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}

	// HTTP GET request for image
	private byte[] sendGetForImage(String url) throws Exception {

		URL obj = new URL(url);

		HttpGet httpRequest = new HttpGet(obj.toURI());
		HttpClient httpclient = new DefaultHttpClient();

		HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);

		HttpEntity entity = response.getEntity();

		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);

		InputStream instream = bufHttpEntity.getContent();

		return getBytes(instream);

	}

	public String defineRequestURL() {

		ConnectionData cd = ConnectionData.getInstance();
		String serverURL = cd.getServer();
		int Port = cd.getPort();

		String servletContext = "/TestTDSServer/TestServer?date=";
		String quest = "&quest=data";

		Calendar card = Calendar.getInstance();

		int year = card.get(Calendar.YEAR);
		int month = card.get(Calendar.MONTH);
		int day = card.get(Calendar.DAY_OF_MONTH);
		if (month == Calendar.JANUARY) {
			month = Calendar.DECEMBER;
			year--;
		} else {
			month--;
			if (day > 28) {
				day = 28;
			}
		}
		// because of month starts by 0, adding by 1 to get real month
		month++;

		String date = "y" + String.valueOf(year);
		date += "m" + String.valueOf(month);
		date += "d" + String.valueOf(day);

		String URL = "http://" + serverURL + ":" + String.valueOf(Port)
				+ servletContext + date + quest + "&id=null";

		Log.v("ConnectionString", URL);

		return URL;
	}

	public String getURLforImageQuest(String id) {
		ConnectionData cd = ConnectionData.getInstance();
		String serverURL = cd.getServer();
		int Port = cd.getPort();

		String servletContext = "/TestTDSServer/TestServer?id=";
		String quest = "&quest=image";

		String URL = "http://" + serverURL + ":" + String.valueOf(Port)
				+ servletContext + id + quest;

		Log.v("ConnectionString", URL);

		return URL;
	}

	public void getMessagesFromString(String str) {
		Log.v("Response", str);
		String[] msgArray = str.split("#");
		if (str != "") {
			msgMnmgr.clearList();

			for (String s : msgArray) {
				System.out.println(s);
				String[] msg = s.split(";");

				Calendar datum = new GregorianCalendar(
						Integer.parseInt(msg[0]), Integer.parseInt(msg[1]) - 1,
						Integer.parseInt(msg[2]), Integer.parseInt(msg[3]),
						Integer.parseInt(msg[4]), Integer.parseInt(msg[5]));

				TDSMessage n = new TDSMessage();
				n.setDatum(datum);
				n.setType(notificationMnmgr.getTypeByString(msg[6]));
				n.setLatitude(Double.valueOf(msg[7]));
				n.setLongitude(Double.valueOf(msg[8]));
				try {
					n.setImage(sendGetForImage(getURLforImageQuest(msg[9])));
					// n.setImage(null);
					// Log.e("setImage to Ntotify", "success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("setImage to Ntotify", "failed");
				}
				msgMnmgr.addNotification(n);
			}
		}
	}

	public Boolean checkNewMessage() {
		if (msgMnmgr.sizeOfList() > msgMnmgr.getOldSizeOfList()) {
			return true;
		}
		return false;
	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	// // HTTP POST request
	// private void sendPost() throws Exception {
	//
	// String url = "https://www.tds.de";
	// URL obj = new URL(url);
	// HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	//
	// //add reuqest header
	// con.setRequestMethod("POST");
	// con.setRequestProperty("User-Agent", USER_AGENT);
	// con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	//
	// String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	//
	// // Send post request
	// con.setDoOutput(true);
	// DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	// wr.writeBytes(urlParameters);
	// wr.flush();
	// wr.close();
	//
	// int responseCode = con.getResponseCode();
	// System.out.println("\nSending 'POST' request to URL : " + url);
	// System.out.println("Post parameters : " + urlParameters);
	// System.out.println("Response Code : " + responseCode);
	//
	// BufferedReader in = new BufferedReader(
	// new InputStreamReader(con.getInputStream()));
	// String inputLine;
	// StringBuffer response = new StringBuffer();
	//
	// while ((inputLine = in.readLine()) != null) {
	// response.append(inputLine);
	// }
	// in.close();
	//
	// //print result
	// System.out.println(response.toString());
	//
	// }
}
