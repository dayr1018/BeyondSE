
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {
//	private Calculator cal;
	String str; //= "##Header";
	Header header; //= new Header(str);
	
	@Before
	public void setup(){
		str = "##Header";
		header = new Header(str);
		System.out.println("setup");
	}
	
	@Test
	public void Header(String str) {
		String testStr = header.getString();
		assertEquals("<h2>Header</h2>", testStr);
		System.out.println("test");
	}

	@After
	public void teardown(){
		System.out.println("teardown");
	}
	
}