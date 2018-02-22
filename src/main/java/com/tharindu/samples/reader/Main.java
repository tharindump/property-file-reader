package com.tharindu.samples.reader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		PropertyReaders readers = PropertyReaders.getInstance();
		PropertyFileReader fileReader = readers.getPropertyFileReader("system.properties");
		System.out.println(fileReader.getProperty("email"));
		
	}

}
