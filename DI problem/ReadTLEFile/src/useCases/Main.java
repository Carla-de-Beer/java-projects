package useCases;

import java.io.IOException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static final String XML_FILE_NAME = "Beans.xml";

	public static void main(String[] args) throws IOException, FileEmptyException,
	FileIncorrectFormatException, FileIncorrectDataException {

	AbstractApplicationContext context = 
            new ClassPathXmlApplicationContext(XML_FILE_NAME);	
	 try{ 		      	 
		    IReadTLEFile object = (ReadTLEFile) context.getBean("requestObject", ReadTLEFile.class);
		    object.readFile();
		 }
	    finally{
	    	context.close();
	    }
	}
}
