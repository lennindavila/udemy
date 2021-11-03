import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Search {

	public static void main(String[] args) throws IOException {
		System.out.println("ok");
		String text = "import";
		String token =";"; 
		String extension = ".java";
		
		List<File> files = new ArrayList<>();
		Search search = new Search();
		String path = "M:\\Proyectos\\Personales\\Github\\Udemy\\Angular11 + Spring5 WebFullStack\\backend\\spring-boot-backend-apirest\\src\\main\\java";
		String path1 = "M:\\Proyectos\\Personales\\Github\\Udemy\\Microservices\\workspace";
		search.listf(path,files);
		search.listf(path1,files);
		
		List<String> lines = new ArrayList<>();
		for (File file : files) {
			  BufferedReader br = new BufferedReader(new FileReader(file));
			  if(file.getName().endsWith(extension)) {
				  String st;			  
				  while ((st = br.readLine()) != null) {
					  int endIndex = st.indexOf(token);
					  if(st.startsWith(text)) {
						  lines.add(st.substring(0,endIndex));
					  }
				  }	  
			  }
			  
		}
		
		System.out.println("lines size: " + lines.size());
		
		//lines.forEach(System.out::println);
		lines = lines.stream().distinct().filter(s -> !s.contains("bolsadeideas") || !s.contains("formacionbdi")).collect(Collectors.toList());
		System.out.println("lines size:: " +lines.size());
		lines.forEach(System.out::println);
	}
	
	public void listf(String directoryName, List<File> files) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null) {
	        for (File file : fList) {      
	            if (file.isFile()) {
	                files.add(file);
	            } else if (file.isDirectory()) {
	                listf(file.getAbsolutePath(), files);
	            }
	        }
	    }
	}	
	
	
}
