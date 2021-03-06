import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Node{

	ArrayList<Token> tokens = new ArrayList<Token>();
	String string;
	String[] strArr={""};


	Pattern pEm = Pattern.compile("[*][A-Za-z0-9./: ]+[*]|[_][A-Za-z0-9./: ]+[_]");
	Pattern pStrong = Pattern.compile("[**][A-Za-z0-9./: ]+[**]|[__][A-Za-z0-9./: ]+[__]");
	//Pattern pLink = Pattern.compile("!\\[[A-Za-z0-9.*]*\\]\\([A-Za-z0-9/:.]*\\)");
	Pattern pTotal = Pattern.compile("[*][A-Za-z0-9./: ]+[*]|[_][A-Za-z0-9./: ]+[_]|[**][A-Za-z0-9./: ]+[**]|[__][A-Za-z0-9./: ]+[__]");

	Matcher mEm, mStrong, mLink, mTotal, mCount;

	Node(){}

	Node(String str)
	{
		string = str;

		Token token = new Token();
	}

	String getString()
	{
		return string.toString();
	}

}

class Header extends Node{


	Header(){}

	Header(String str)
	{
		string = str;
	}

	String getString()
 	{



	      if(string.charAt(5)=='#' && string.charAt(4)=='#' && string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
		 string = string.substring(6, string.length());  //string = string.substring(6);
		 return ("<h6>" + string.toString() + "</h6>");
	      }
	      else if(string.charAt(4)=='#' && string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
		 string = string.substring(5, string.length());  //string = string.substring(5);
		 return ("<h5>" + string.toString() + "</h5>");
	      }
	      else if(string.charAt(3)=='#' && string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
		 string = string.substring(4, string.length());  //string = string.substring(4);
		 return ("<h4>" + string.toString() + "</h4>");
	      }
	      else if(string.charAt(2)=='#' && string.charAt(1)=='#' && string.charAt(0)=='#') {
		 string = string.substring(3, string.length());  //string = string.substring(3);
		 return ("<h3>" + string.toString() + "</h3>");
	      }
	      else if(string.charAt(1)=='#' && string.charAt(0)=='#') {
		 string = string.substring(2, string.length());  //string = string.substring(2);
		 return ("<h2>" + string.toString() + "</h2>");
	      }
	      else if(string.charAt(0)=='#'){
		 string = string.substring(1, string.length());  //string = string.substring(1);
		 return ("<h1>" + string.toString() + "</h1>");
	      }
	      else
		 return ("<h1>" + string.toString() + "</h1>");
	 }
}

class CodeBlock extends Node{

	CodeBlock(){}

	CodeBlock(String str)
	{
		string = str;
	}

	String getString()
	{
		return "<pre><code>" + string.toString() + "\n</code></pre>";
	}
}

class List extends Node{

	List(){}

	List(String str)
	{
		string = str;
	}

	String getString()
	{
		return "\n<ul>" + string.toString() + "\n</ul>";
	}
}

class Image extends Node{

	String alt="";
	String src="";

	int altStart, altEnd, srcStart, srcEnd;

	Image(){}

	Image(String str)
	{
		altStart = str.indexOf("[");
		altEnd = str.indexOf("]");
		srcStart= str.indexOf("(");
		srcEnd = str.indexOf(")");

		for(int i = altStart + 1; i < altEnd; i++)
			alt = alt + str.charAt(i);

		for(int i = srcStart + 1; i < srcEnd; i++)
			src = src + str.charAt(i);

	}

	String getString()
	{
		return "\n\n<img src=\"" +src + "\" alt =\"" + alt + "\" />";
	}
}
