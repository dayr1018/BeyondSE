class Token{

	String style;
	String string="";

	Token(){}

	Token(String str)
	{
		string = str;
	};

	String getString()
	{
		return string.toString();
	}
}

class NormalString extends Token{

	NormalString(String str)
	{
		string  = str;
	}
}

class Emphasis extends Token{

	Emphasis(String str)
	{
		string = str;
	}

	String getString()
	{
		string = string.substring(1, string.length()-1);
		return "<em>" + string + "</em>";//.toString();
	}
}

class Strong extends Token{

	Strong(String str)
	{
		string = str;
	}

	String getString()
	{
		string = string.substring(3, string.length()-3);
		return ("<strong>" + string + "</strong>").toString();
	}

}

class Link extends Token{
}