package com.sauce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static java.lang.System.*;

//use this class to read properties files
public class ReadProperty {
	static String filePath = System.getProperty("user.dir") + "\\XML\\config.properties";
	private String username, password, URL, env, pcName, browser;

	public ReadProperty() throws IOException {
		Properties prop = new Properties();
		FileInputStream instream = new FileInputStream(filePath);
		prop.load(instream);
		this.username = prop.getProperty("username");
		this.password = prop.getProperty("password");
		this.URL = prop.getProperty("URL");
		this.env = prop.getProperty("enviroment");
		this.pcName = prop.getProperty("pcName");
		this.browser = prop.getProperty("browser");
		instream.close();
	}

	/********************** GETTERS ***************************/
	public String getUserName() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getURL() {
		return this.URL;
	}

	public String getEnviroment() {
		return this.env;
	}

	public String getPcName() {
		return this.pcName;
	}

	public String getBrowser() {
		return this.browser;
	}
}
