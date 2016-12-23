

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class ImageTest {
	Image image;
	String src = "https://camo.githubusercontent.com/ecb5a6065d59c62640d48a1fb53cd9ccad47e017/68747470733a2f2f732d6d656469612d63616368652d616b302e70696e696d672e636f6d2f373336782f35622f61372f61312f35626137613163396431323030613433613462643963373662623736353638612e6a7067";
	String alt = "Image";
	
	@Before
	public void setup(){
		image = new Image("[Image](https://camo.githubusercontent.com/ecb5a6065d59c62640d48a1fb53cd9ccad47e017/68747470733a2f2f732d6d656469612d63616368652d616b302e70696e696d672e636f6d2f373336782f35622f61372f61312f35626137613163396431323030613433613462643963373662623736353638612e6a7067))");
	}
	
	@Test
	public void test() {
		assertEquals("\n\n<img src=\"" +src + "\" alt =\"" + alt + "\" />", image.getString());

		//Error Test
		//assertEquals("\n\n<img src=\"" +alt + "\" alt =\"" + src + "\" />", image.getString());
	}

}

/*
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

*/