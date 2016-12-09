
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Node;

public class HeaderTest {
	Header header;
	
	@Before
	public void setup(){
		header = new Header("# Head");
	}
	
	@Test
	public void test() {
		assertEquals("<h2>" +" Head"+ "</h2>",header.getString());
	}

}

/*
 * 

public class Header extends Node{

	public Header(){}

	public Header(String str)
	{
		string = str;
	}

	public String getString()
 	{
      if(string.charAt(5)=='#' && string.charAt(4)=='#' && string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
         string = string.substring(6);
         return ("<h6>" + string.toString() + "</h6>");
      }
      else if(string.charAt(4)=='#' && string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
         string = string.substring(5);
         return ("<h5>" + string.toString() + "</h5>");
      }
      else if(string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
         string = string.substring(4);
         return ("<h4>" + string.toString() + "</h4>");
      }
      else if(string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
         string = string.substring(3);
         return ("<h3>" + string.toString() + "</h3>");
      }
      else if(string.charAt(1)=='#' && string.charAt(0)=='#') {
         string = string.substring(2);
         return ("<h2>" + string.toString() + "</h2>");
      }
      else if(string.charAt(0)=='#'){
         string = string.substring(1);
         return ("<h1>" + string.toString() + "</h1>");
      }
      else
         return ("<h1>" + string.toString() + "</h1>");
    }
}

 * */
