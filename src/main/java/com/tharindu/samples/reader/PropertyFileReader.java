package com.tharindu.samples.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * PropertyFileReader class is created using Java File I/O <br>
 * The reader reads the given property file in the classpath and provides a
 * method to retrieve a property value for given proerty name.
 * 
 * @author Tharindu Madusanka
 */
public class PropertyFileReader {

	private Map<String, String> properties;
	private String fileName;

	PropertyFileReader(String fileName) {
		this.fileName = fileName;
	}

	void setProperties() throws Exception {

		FileReader fileReader = null;
		BufferedReader buffer = null;

		try {
			this.properties = new HashMap<String, String>();

			/*
			 * 
			 * -----------------------------------------------------------------
			 * -- | | | Classpath issue is here, we have to fix it before
			 * releasing it | | |
			 * -----------------------------------------------------------------
			 * --
			 * 
			 */

			URL fileUrl = getClass().getResource("/" + fileName);
			File propertyFile = new File(fileUrl.getFile());

			fileReader = new FileReader(propertyFile);
			buffer = new BufferedReader(fileReader);

			String currentLine;
			int lineNo = 0;

			while ((currentLine = buffer.readLine()) != null) {
				lineNo++;
				if (currentLine.matches(".+=.+")) {
					String keyvalue[] = currentLine.split("=");
					this.properties.put(keyvalue[0], keyvalue[1]);
				} else {
					throw new Exception("Error in the properties file. [key=value] format is missing in line number "
							+ lineNo + ".");
				}
			}
		} finally {
			buffer.close();
			fileReader.close();
		}

	}

	/**
	 * Takes the property name as argument and returns the corresponding
	 * property value from the file.<br> 
	 * <p>If the returned property is null, make sure
	 * the property name specified in the argument matches with the property name in the
	 * properties file.</p>
	 * 
	 * @param propertyName
	 *            name of the proprty
	 * @return the corresponding property value
	 */
	public String getProperty(String propertyName) {

		String property = properties.get(propertyName);

		return property;
	}

}
