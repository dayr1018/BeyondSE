import java.util.*;

class Document{

	ArrayList<Node> nodes = new ArrayList<Node>();
	String mdFile, htmlFile;

	Document(){};

	Document(String str1)
	{
		htmlFile = str1.substring(0, str1.length()-3);  //str1 is md file.
	}

	Document(String str1, String str2)
	{
			mdFile = str1.substring(0, str1.length()-3);
			htmlFile = str2.substring(0, str2.length()-5);
	}

	//method
	void addNode(Node node)
	{
		nodes.add(node);
	}

	int numOfNodes()
	{
		return nodes.size();
	}

	Node getNode(int i)
	{
		return nodes.get(i);
	}

	void setHtmlFileName(String html)
	{
		htmlFile = html.toString();
	}

	String getHtmlFileName()
	{
		return htmlFile.toString();
	}

	void setMdFileName(String md)
	{
		mdFile = md.toString();
	}

	String getMdFileName()
	{
		return mdFile.toString();
	}

}

