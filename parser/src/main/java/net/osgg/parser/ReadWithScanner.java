package net.osgg.parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;


/** Assumes UTF-8 encoding. JDK 7+. */
public final class ReadWithScanner {
	  int alto = 0, viva = 0, persona = 0, cielo = 0;
  /**
   Constructor.
   @param fileName full name of an existing, readable file.
  */
  public ReadWithScanner(String fileName){
    filePath = Paths.get(fileName);
  }
  
  
  /** Template method that calls {@link #processLine(String)}.  */
  public final void processLineByLine() throws IOException {
    try (Scanner scanner =  new Scanner(filePath, ENCODING.name())){
      while (scanner.hasNextLine()){
        processLine(scanner.nextLine());
      }    
      System.out.println("La palabra alto se repite " + alto + " veces");
      System.out.println("La palabra viva se repite " + viva + " veces");
      System.out.println("La palabra persona se repite " + persona + " veces");
      System.out.println("La palabra cielo se repite " + cielo + " veces");
    }
  }
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple name-value pairs, separated by an 
   '=' sign. Examples of valid input: 
   <tt>height = 167cm</tt>
   <tt>mass =  65kg</tt>
   <tt>disposition =  "grumpy"</tt>
   <tt>this is the name = this is the value</tt>
  */
  protected void processLine(String line){
    //use a second Scanner to parse the content of each line 
    try(Scanner scanner = new Scanner(line)){
    	//el segundo parametro es para los delimitadores, el tercero es pa q se muestre los delimitadores
    	StringTokenizer st = new StringTokenizer(line, ",. ", true);
    	while (st.hasMoreElements()) {
    		String _token = st.nextToken();
    		//System.out.println(_token);
    		switch (_token.trim()) {
	    		case "alto":
	    			alto++;
	    			break;
	    		case "viva":
	    			viva++;
	    			break;
	    		case "persona":
	    			persona++;
	    			break;
	    		case "cielo":
	    			cielo++;
	    			break;
    		}
        }
    }
    
  }
  
  // PRIVATE 
  private final Path filePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private static void log(Object object){
    System.out.println(Objects.toString(object));
  }
  
  private String quote(String text){
    String QUOTE = "'";
    return QUOTE + text + QUOTE;
  }
} 

