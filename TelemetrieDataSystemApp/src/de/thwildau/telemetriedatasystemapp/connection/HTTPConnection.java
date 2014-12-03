package de.thwildau.telemetriedatasystemapp.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import android.os.AsyncTask;
import android.util.Log;
import de.thwildau.telemetriedatasystemapp.data.ConnectionData;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;
import de.thwildau.telemetriedatasystemapp.test.TDSTest;

public class HTTPConnection extends AsyncTask<String, Void, String> {

	private Exception exception;
	private final String USER_AGENT = "Mozilla/5.0";

	@Override
	protected String doInBackground(String... arg0) {
		
		TDSTest test = new TDSTest();
		
		try {
			sendGet();
			Boolean result = checkNewMessage();
			if(result = true){
				return "new";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "notnew";
		}
		return "notnew";
	}

	// HTTP GET request
	private Boolean sendGet() throws Exception {

		String url = defineRequestURL();

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		Log.v("Sending 'GET' request to URL", url);
		Log.v("ResponseCode", String.valueOf(responseCode));

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			//response.append(inputLine);
			getMessageFromString(inputLine);
			Log.v("Response", inputLine);
		}
		in.close();

		return true;

	}

	public String defineRequestURL() {

		ConnectionData cd = ConnectionData.getInstance();
		String serverURL = cd.getServer();
		int Port = cd.getPort();

		String servletContext = "/TestTDSServer/TestServer?date=";

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
				+ servletContext + date;

		Log.v("ConnectionString", URL);

		return URL;
	}

	public TDSMessage getMessageFromString(String Message) {
		Log.v("Response", Message);
		return new TDSMessage();
	}

	public Boolean checkNewMessage(){
		
		return true;
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
