

public class List extends Node{


	public List(String str)
	{
		string = str;
	}


	public String getString()
	{
		return "\n<ul>" + string.toString() + "\n</ul>";
	}
}