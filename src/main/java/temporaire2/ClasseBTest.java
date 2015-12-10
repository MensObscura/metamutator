package temporaire2;

import org.junit.Assert;
import org.junit.Test;

import temporaire.ClasseB;

public class ClasseBTest {
	
	static ClasseB foo = new ClasseB();
	
	@Test
    public void testS() {
        Assert.assertEquals("toto",  foo.s());
    }
	
	@Test
    public void testOp() {
        Assert.assertEquals(true,  foo.op(false, true));
        Assert.assertEquals(true,  foo.op(true, false));
        Assert.assertEquals(true,  foo.op(true, true));
        Assert.assertEquals(false, foo.op(false, false));
    }

    @Test
    public void testOp2() {
        Assert.assertEquals(true, foo.op2(7, 2));
        Assert.assertEquals(false, foo.op2(1, 2));
        Assert.assertEquals(false, foo.op2(2, 2));
    }

    @Test
    public void testOp3() {
        Assert.assertEquals(false, foo.op3(new java.lang.String().getClass()));
        
    }

}
