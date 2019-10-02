package utilities;

import java.io.FileInputStream;

import java.util.Properties;

public class PropertyfileUtil
{
	public static String getValueForKey(String key) throws Throwable
	{
		Properties configproperties=new Properties();
		FileInputStream fis=new FileInputStream("D:\\srinivas\\StockAcounting\\PropertyFiles\\Environment.properties");
		configproperties.load(fis);
		return configproperties.getProperty(key);
	}

}
