# Property File Reader

This project is a simple property file reader which can be used to read *.properties files in the project classpath, or in a maven project src/main/resource folder.
The `property-file-reader` can manage multiple property files in the classpath, and can access any property file in the classpath using the `PropertyReaders` class. `PropertyFileReader` class manages a single property file and using its `getProperty(property_name)` method can retrieve the properties in the property file.

## Installing

 If you are using maven, clone the project to your workspace and install into your local maven repository using
 ```
maven clean install
 ```

Then add as a dependancy in your project `pom.xml`
 ```
<dependency>
    <artifactId>property-file-reader</artifactId>
    <groupId>com.tharindu.samples</groupId>
    <version>1.2.1</version>
</dependency>
 ```
 or add the jar file in `out/` folder to your build path.

 ## Usage

 Get the instance of `PropertyReaders` object. This will be a singleton object and it keeps all all `PropertyFileReaders` in the application. 
 ```
 PropertyReaders readers = PropertyReaders.getInstance();
 ```

 Once you get the `PropertyReaders` object, you can register a new `PropertyFileReader` to a property file and access its properties. 
 
 A single property file will be mapped to a single `PropertyFileReader` throughout the application lifecycle and even you tried to register the same property file twice the same Reader will be returned. To access a property by its name you can use `getProperty(property_name)` method in `PropertyFileReader` class.

 ```
 PropertyFileReader fileReader = readers.getPropertyFileReader("filename.properties");
 String propertyValue = fileReader.getProperty("PropertyName");
 ```