
import org.w3c.tidy.Node;
import org.w3c.tidy.Tidy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Validator {
   
   Validator(){};
   
   void readFile(String fileName){
      
      try{
         
         FileReader fr = new FileReader(fileName);
         BufferedReader br = new BufferedReader(fr);
         
         String line = "";
         String total = "";         
         for(int i=0; (line = br.readLine()) != null; i++)
            total = total.concat(line);
            
         if(isValid(total))
            System.out.println("Valid");
         else
            System.out.println("Invalid");

      }catch(IOException e){}
         
   }

   boolean isValid(String htmlData){

      Tidy tidy = new Tidy();
      InputStream stream = new ByteArrayInputStream(htmlData.getBytes(StandardCharsets.UTF_8));
      tidy.parse(stream, System.err);
       
      return (tidy.getParseErrors()+tidy.getParseWarnings() == 0);//(tidy.getParseErrors() == 0);
   }

}
