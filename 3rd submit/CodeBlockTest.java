
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CodeBlockTest {
	
	String str;
	CodeBlock codeblock;
	
	@Before
	public void setup(){
		codeblock = new CodeBlock("CodeBlock");
	}
	
	@Test
	public void test() {
		
		assertEquals("<pre><code>"+"CodeBlock"+"\n</code></pre>",codeblock.getString());
	}
	
	@After
	public void teardown(){
		fail("Teardown Not yet implemented");
	}
	
}