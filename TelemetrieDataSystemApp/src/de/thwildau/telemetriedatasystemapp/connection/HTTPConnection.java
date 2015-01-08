package de.thwildau.telemetriedatasystemapp.connection;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

/**
 * HTTP Connection class is used for the connection with the server
 * @author Fabian
 *
 */
public class HTTPConnection extends AsyncTask<String, Void, String> {
	
	//define user agent
	private final String USER_AGENT = "Mozilla/5.0";
	//get manager instances
	NotificationTypeManager notificationMnmgr = NotificationTypeManager
			.getInstance();
	MessageManager msgMnmgr = MessageManager.getInstance();

	/**
	 * predefined method from class extension AsyncTask, entry method
	 */
	@Override
	protected String doInBackground(String... arg0) {
		Boolean result = false;

		try {
			String response = sendGet(defineRequestURL());		//create url, send request, get response
			getMessagesFromString(response);					//create message objects from response
			result = checkNewMessage();							//check messages of new ones
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

	/**
	 *  HTTP GET request for data
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private String sendGet(String url) throws Exception {
		//define URL object from string url
		URL obj = new URL(url);
		//create an open connection to url defined server
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		//receive response code 
		int responseCode = con.getResponseCode();
		Log.v("Sending 'GET' request to URL", url);
		Log.v("ResponseCode", String.valueOf(responseCode));
		
		//save inputstream in a buffered reader
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		//save buffered reader in a Stringbufffer
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//return stringbuffer as string
		return response.toString();

	}

	/**
	 *  HTTP GET request for image
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private byte[] sendGetForImage(String url) throws Exception {
		//define URL object from string url
		URL obj = new URL(url);
		//create HttpGet and HttpClient object from URL
		HttpGet httpRequest = new HttpGet(obj.toURI());
		HttpClient httpclient = new DefaultHttpClient();
		//execute request and save response
		HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);
		//get response entry
		HttpEntity entity = response.getEntity();
		//read response entry
		BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
		//get content of buffered entry
		InputStream instream = bufHttpEntity.getContent();
		//return bytes of the input stream 
		return getBytes(instream);

	}

	/**
	 * method that creates the request URL for receiving message data with defined user data
	 * @return URL string to get message data from server
	 */
	public String defineRequestURL() {
		//get user data from connection data manager
		ConnectionData cd = ConnectionData.getInstance();
		String serverURL = cd.getServer();
		int Port = cd.getPort();
		
		//set path of servlet and type of request
		String servletContext = "/Web?date=";
		String quest = "&quest=data";

		//get date from today one month ago
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		//concatenate URL string
		String URL = "http://" + serverURL + ":" + String.valueOf(Port)
				+ servletContext + cal.getTimeInMillis() + quest;

		Log.v("ConnectionString", URL);

		return URL;
	}

	/**
	 * method that creates the request URL for receiving image of message with defined user data
	 * @param id - of the requested attachment
	 * @return URL 
	 */
	public String getURLforImageQuest(String id) {
		//get user data from connection data manager
		ConnectionData cd = ConnectionData.getInstance();
		String serverURL = cd.getServer();
		int Port = cd.getPort();

		//set path of servlet and type of request
		String servletContext = "/Web?date=";
		String quest = "&quest=image";

		//concatenate URL string
		String URL = "http://" + serverURL + ":" + String.valueOf(Port)
				+ servletContext + id + quest;

		Log.v("ConnectionString", URL);

		return URL;
	}

	/**
	 * method to set message objects to message manager from server response string
	 * @param str - string of the server response that should be analyzed 
	 */
	public void getMessagesFromString(String str) {
		Log.v("Response", str);
		List<TDSMessage> list = new ArrayList<TDSMessage>();
		String[] msgArray = str.split("#");
		if (str != "") {
			try{
				for (String s : msgArray) {
					System.out.println(s);
					String[] msg = s.split(";");
	
					//get date
					Calendar datum = Calendar.getInstance();
					datum.setTimeInMillis(Long.valueOf(msg[0]));
					//create new message and set attributes
					TDSMessage n = new TDSMessage();
					n.setDatum(datum);
					n.setType(notificationMnmgr.getType(Integer.valueOf(msg[1])));
					n.setLatitude(Double.valueOf(msg[2]));
					n.setLongitude(Double.valueOf(msg[3]));
					//try to get image of the message from server
					try {
						n.setImage(sendGetForImage(getURLforImageQuest(msg[0])));
					} catch (Exception e) {
						e.printStackTrace();
						Log.e("setImage to Ntotify", "failed");
					}
					list.add(n);				
				}
			}catch (Exception e){
				e.printStackTrace();
				Log.e("get Messages from String", "failed");
			}
			//clear old messages and set new ones
			msgMnmgr.clearList();
			msgMnmgr.setNotifyList(list);			
		}
	}

	/**
	 * method to check message list for new messages
	 * @return boolean value - true = new messages - false = no new messages
	 */
	public Boolean checkNewMessage() {
		if (msgMnmgr.sizeOfList() > msgMnmgr.getOldSizeOfList()) {
			return true;
		}
		return false;
	}

	/**
	 * method to get bytes from an input stream 
	 * @param is - input stream is needed
	 * @return byte array of input stream
	 * @throws IOException
	 */
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
