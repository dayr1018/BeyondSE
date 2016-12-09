import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Node{

   ArrayList<Token> tokens = new ArrayList<Token>();
   String string;

   Pattern p = Pattern.compile("!\\[[A-Za-z0-9.*]*\\]\\([A-Za-z0-9/:.]*\\)");

   Matcher mHeaderA, mHeaderB, mDoubleSpace, mCodeBlock, mBlockQuote, mList, mImage;

   Node(){}

   Node(String str)
   {
      string = str;

      Token token = new Token(str);

   }

   String getString()
   {
      return string.toString();
   }
}