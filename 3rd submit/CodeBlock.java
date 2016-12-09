
public class CodeBlock extends Node{

	public CodeBlock(){}

	public CodeBlock(String str)
	{
		string = str;
	}

	public String getString()
	{
		return "<pre><code>" + string.toString() + "\n</code></pre>";
	}
}