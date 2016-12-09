import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser{

   ArrayList<String> buf = new ArrayList<String>();
   Pattern pHeader = Pattern.compile("^[#]+[ A-Za-z#]*");
   Pattern pEnd = Pattern.compile("^[ A-Za-z#.*]*[ ][ ]$");
   //Pattern pSingleLine =
   Pattern pMultiStart = Pattern.compile("<pre><code>[A-Za-z#.*() ]*|^[*][A-Za-z0-9 ]+$|^[+][A-Za-z0-9 ]+$|^[-][A-Za-z0-9 ]+$");
   //Pattern pMultiStart = Pattern.compile("<pre><code>[A-Za-z#.*() ]*");
   Pattern pMultiEnd = Pattern.compile("[A-Za-z#.* ]*</code></pre>");
   Matcher mHeader, mEnd, mMultiStart, mMultiEnd;
   int kind;

   Parser(){kind = 0;}

   void parse(String fileName)
   {
      try
      {

         FileReader fr = new FileReader(fileName);
         BufferedReader br = new BufferedReader(fr);

         String line = "";
         for(int i = 0; (line = br.readLine())!=null; i++)
         {
            /*
            mMultiStart = pMultiStart.matcher(line);
            if(mMultiStart.find())
            {
               System.out.println(mMultiStart.group());
            }
            */

            switch(kind)
            {
               case 0:
                  mMultiStart = pMultiStart.matcher(line);
                  if(mMultiStart.find())
                  {
                     //새로운 multi line을 buf에 저장하기 전에 buf에 있는 애들 모두 출력(나중엔 노드에 전달)
                     for(int j = 0; j < buf.size(); j++)
                        System.out.println(buf.get(j) + "\t\t(single line)");

                     buf.clear();

                     buf.add(line);//buf.add(mMultiStart.group());

                     kind = 1;
                     break;
                  }
                  buf.add(line);
                  break;
               case 1:
                  mMultiEnd = pMultiEnd.matcher(line);
                  if(mMultiEnd.find())
                  {
                     buf.add(line);
                     //multi line 끝났으므로 저장해놧던 buf 모두 출력(나중엔 노드에 전달)
                     for(int j = 0; j < buf.size(); j++)
                        System.out.println(buf.get(j) + "\t\t(multi line)");

                     buf.clear();

                     kind = 0;
                     break;
                  }

                  buf.add(line);
                  break;
            }

            /*
            mHeader = pHeader.matcher(line);
            mEnd = pEnd.matcher(line);

            if(mHeader.find())
               System.out.println(mHeader.group() + "\t\t(parsing - header)");
            else if(mEnd.find())
               System.out.println(mEnd.group() + "\t\t(parsing - end)");
            else if(line.length() == 0)
               System.out.println(line + "\t\t(parsing - end)");
            else
               System.out.println(line);
            */
         }

         br.close();
      }catch(IOException e){}

   }

}