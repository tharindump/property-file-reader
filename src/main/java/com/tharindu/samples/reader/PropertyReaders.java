package com.tharindu.samples.reader;

import java.util.HashMap;
import java.util.Map;

/**
 * PropertyReaders is a singleton class which used as a store for keeping list
 * of {@link PropertyFileReader} instances. Each PropertyFileReader instance is
 * mapped with its filename, and using methods the corresponding reader can be
 * retrieved. There will be only one instance to manage each property file and
 * the PropertyReaders class will check each PropertyFileReader instance is
 * available before returning it.
 * 
 * @author Tharindu Madusanka
 *
 */
public class PropertyReaders {

	private Map<String, PropertyFileReader> map;

	private static PropertyReaders instance = null;

	private PropertyReaders() {
		this.map = new HashMap<String, PropertyFileReader>();
	}

	public static PropertyReaders getInstance() {

		if (instance == null) {
			instance = new PropertyReaders();
		}

		return instance;
	}

	private void setNewPropertyReader(String filename) throws Exception {

		PropertyFileReader reader = new PropertyFileReader(filename);
		reader.setProperties();
		map.put(filename, reader);
	}

	/**
	 * Returns singleton {@link PropertyFileReader} instance for a given
	 * property filename.
	 * 
	 * @param filename name of the property file
	 * @return
	 * @throws Exception 
	 */
	public PropertyFileReader getPropertyFileReader(String filename) throws Exception {
		
		if(!filename.matches(".*\\.properties")) {
			throw new Exception("Specified file is not a property file: \""+filename+"\"");
		}

		if (!map.containsKey(filename)) {
			setNewPropertyReader(filename);
		}

		PropertyFileReader propertyFileReader = map.get(filename);

		return propertyFileReader;
	}

}
