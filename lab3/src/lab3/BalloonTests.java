package lab3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import balloon4.Balloon;

public class BalloonTests 
{
	
	private Balloon balloon1;
	private Balloon balloon2;
	
	@Before
	public void setup() 
	{
		balloon1 = new Balloon(10);
	}
	
	@Test
	public void testInitialRadius()
	{
		String msg = "New balloon should have radius 0.";
		assertEquals(msg, 0, balloon1.getRadius());
	}
	
	@Test
	public void testInitialPop()
	{
		String msg = "New balloon should not be popped.";
		assertFalse(msg, balloon1.isPopped());
	}
	
	@Test
	public void testBlowMethod()
	{
		String msg = "Balloon constructed w/ max radius 10 and blown w/ 5 should have radius 5.";
		balloon1.blow(5);
		assertEquals(msg, 5, balloon1.getRadius());
	}
	
	@Test
	public void testBlowTwice()
	{
		String msg = "Balloon w/ max radius 10 should have radius 10 after calling blow method w/ 5 twice.";
		balloon1.blow(5);
		balloon1.blow(5);
		assertEquals(msg,10,balloon1.getRadius());
	}
	
	@Test
	public void testPopMethod()
	{
		String msg = "Balloon after pop should have radius 0.";
		balloon1.pop();
		assertEquals(msg, 0, balloon1.getRadius());
	}
	
	@Test
	public void testPopMethod2()
	{
		String msg = "Balloon after pop should return as popped.";
		balloon1.pop();
		assertTrue(msg, balloon1.isPopped());
	}
	
	@Test
	public void testDeflateMethod()
	{
		balloon1.deflate();
		String msg = "Balloon after deflating should have radius 0.";
		assertEquals(msg, 0, balloon1.getRadius());
	}
	
	@Test
	public void testDeflateMethod2()
	{
		balloon1.deflate();
		String msg = "Balloon after delfating should not be popped.";
		assertFalse(msg, balloon1.isPopped());
	}
	
	@Test
	public void testBlowPop()
	{
		String msg = "Balloon after being blown beyond maximum radius should pop.";
		balloon1.blow(100);
		assertTrue(msg, balloon1.isPopped());
	}
	
	@Test
	public void testNegativeRadius()
	{
		String msg = "Balloon made w/ negative radius should be 0.";
		balloon2 = new Balloon(-9);
		assertEquals(msg, 0, balloon2.getRadius());
	}
	
	@Test
	public void testBlowPopped()
	{
		String msg = "Blow method should have no effect on a popped balloon.";
		balloon1.pop();
		balloon1.blow(5);
		assertEquals(msg,0,balloon1.getRadius());
	}
	
	@Test
	public void testNegativeBlow()
	{
		String msg = "If given balloon radius is negative, blow should have no effect on the balloon.";
		balloon2 = new Balloon(-9);
		balloon2.blow(5);
		assertEquals(msg, 0, balloon2.getRadius());
	}
}
