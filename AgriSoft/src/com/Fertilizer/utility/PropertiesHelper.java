package com.Fertilizer.utility;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper 
{
	public static Properties marathiProperties;
	static Properties systemProperties ;
	static
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("com/Fertilizer/utility/marathi.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
			marathiProperties = prop ;
			System.out.println("in login"+prop.getProperty("login")+"\t"+prop.getProperty("Reset"));
		}catch(Exception e){
			e.printStackTrace();
		} 
	}
	public static void main(String args[])
	{
		PropertiesHelper helper = new PropertiesHelper();

	}
}
