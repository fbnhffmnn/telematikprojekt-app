package de.thwildau.telemetriedatasystemapp.data;

/**
 * Class "ConnectionData" is for saving connection data between client and server
 * Singleton Pattern is used
 * @author Fabian
 *
 */
public class ConnectionData {

	//instance
	private static ConnectionData instance;
	
	//needed values
	private String server = "192.168.178.36";
	private int port = 8080;				
	private String fahrzeugID = "";		//not used anymore
	private String password = "";		//not used anymore
	
	/**
	 * constructor
	 */
	private ConnectionData() {}
	
	/**
	 * get Instance (threadsave)
	 * @return the singleton object
	 */
	public static synchronized ConnectionData getInstance () {
		if (ConnectionData.instance == null) {
		 	ConnectionData.instance = new ConnectionData ();
		}
		return ConnectionData.instance;
	}

	//------------------------------------------------------
	//getter and setter of the needed values 
	//------------------------------------------------------
	//getter and setter server
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	//getter and setter port
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	//getter and setter fahrzeugid
	public String getFahrzeugID() {
		return fahrzeugID;
	}
	
	public void setFahrzeugID(String fahrzeugID) {
		this.fahrzeugID = fahrzeugID;
	}
	
	//getter and setter password
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
