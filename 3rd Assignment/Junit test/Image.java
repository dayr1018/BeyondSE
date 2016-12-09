

public class Image extends Node{

	String alt="";
	String src="";

	int altStart, altEnd, srcStart, srcEnd;

	public Image(){}

	public Image(String str)
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

	public String getString()
	{
		return "\n\n<img src=\"" +src + "\" alt =\"" + alt + "\" />";
	}
}