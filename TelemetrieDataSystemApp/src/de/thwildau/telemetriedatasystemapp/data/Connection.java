package de.thwildau.telemetriedatasystemapp.data;

public class Connection {

	private static Connection instance;
	
	private String server = "";
	private int port = 0;
	private String fahrzeugID = "";
	private String password = "";
	
	private Connection() {}
	
	public static synchronized Connection getInstance () {
		if (Connection.instance == null) {
		 	Connection.instance = new Connection ();
		}
		return Connection.instance;
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
