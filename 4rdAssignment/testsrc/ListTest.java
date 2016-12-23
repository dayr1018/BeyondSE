
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListTest {
	List list;
	String str;
	
	@Before
	public void setup(){
		//when the error test. list = new List("* list");
		list = new List("list");
	}

	@Test
	public void test() {
		assertEquals("\n<ul>" +"list"+ "\n</ul>", list.getString());
	}
	
	/*
	 	@After
		public void teardown(){
			fail("Teardown Not yet implemented");
		}
	 */
	

}

