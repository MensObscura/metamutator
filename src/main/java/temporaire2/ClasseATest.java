package temporaire2;

import org.junit.Assert;
import org.junit.Test;

import temporaire.ClasseA;

public class ClasseATest {
	
	static ClasseA foo = new ClasseA();
	
	@Test
    public void testS() {
        Assert.assertEquals("toto",  foo.s());
    }

}
