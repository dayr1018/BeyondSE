

/*
import java.util.*;
 
import java.io.*;

public class TestJtidy{
   public static void main(String args[]){

      Validator jtidy = new Validator();

      jtidy.readFile("test.html");
   }
}

*/

import java.util.*;
import java.io.*;

public class TestJtidy{
   public static void main(String args[]){
      Validator jtidy = new Validator();
      Scanner scanner = new Scanner(System.in);
      String command ="";

      while(!command.equals("exit"))
      {
        command = scanner.nextLine();
        if(isThereFile(command))
           jtidy.readFile(command);
      }
     }
   

   static boolean isThereFile(String fileName)
   {

         String s = System.getProperty("user.dir");
         File f= new File(s);
         if(!f.exists())
         {
            System.out.println("Directory doesn't exist.");
            return false;
         }

         File[] allFiles= f.listFiles();
         for(File file : allFiles)
         {
            if(file.getName().equals(fileName))
               
                  return true;
         }

         System.out.println("'" +fileName + "'" + " file doesn't exist.");

         return false;
   }
}
