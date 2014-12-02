package de.thwildau.telemetriedatasystemapp.data;

public class ConnectionData {

	private static ConnectionData instance;
	
	private String server = "";
	private int port = 0;				//not needed
	private String fahrzeugID = "";		//not used anymore
	private String password = "";		//not used anymore
	
	private ConnectionData() {}
	
	public static synchronized ConnectionData getInstance () {
		if (ConnectionData.instance == null) {
		 	ConnectionData.instance = new ConnectionData ();
		}
		return ConnectionData.instance;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getFahrzeugID() {
		return fahrzeugID;
	}
	
	public void setFahrzeugID(String fahrzeugID) {
		this.fahrzeugID = fahrzeugID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
