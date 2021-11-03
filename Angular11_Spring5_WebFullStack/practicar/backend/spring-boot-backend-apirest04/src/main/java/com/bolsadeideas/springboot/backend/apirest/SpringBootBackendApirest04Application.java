package com.bolsadeideas.springboot.backend.apirest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBackendApirest04Application {

	public static void main(String[] args) throws URISyntaxException {
		//SpringApplication.run(SpringBootBackendApirest04Application.class, args);
		
		SpringBootBackendApirest04Application app = new SpringBootBackendApirest04Application();

	        //String fileName = "database.properties";
	        String fileName = "archivo.txt";

	        //System.out.println("getResourceAsStream : " + fileName);
	        InputStream is = app.getFileFromResourceAsStream(fileName);
	        Map<String,String> map = new HashMap<>();
	        
	        printInputStream(is,map);
	      
	        
	        Map<String, String> result = map.entrySet().stream()
	                .sorted(Map.Entry.comparingByKey())
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	        
	        result.forEach((k,v) -> System.out.println((k)) );
	        
	        
	        //lines.forEach(System.out::println);

	        //System.out.println("\ngetResource : " + fileName);
	        //File file = app.getFileFromResource(fileName);
	        //printFile(file);
		
		
		
		
		
	}
	
	
    // get a file from the resources folder
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    /*
        The resource URL is not working in the JAR
        If we try to access a file that is inside a JAR,
        It throws NoSuchFileException (linux), InvalidPathException (Windows)

        Resource URL Sample: file:java-io.jar!/json/file1.json
     */
    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    // print input stream
    private static void printInputStream(InputStream is,Map<String,String> map) {

        try (InputStreamReader streamReader =
                    new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
            	if (line.contains("bolsadeideas") || line.contains("formacionbdi")) continue;
            	map.put(line, line.substring(line.lastIndexOf(".")+1));
                //System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // print a file
    private static void printFile(File file) {

        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
