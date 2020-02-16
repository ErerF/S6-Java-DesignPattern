package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;

public class HashMultiSetTest2 {
	private MultiSet<String> hash;
	private MultiSetDecorator<String> m;
	
	@Before
	public void before() {
		hash = new HashMultiSet<>();
		m=new MultiSetDecorator<String>(hash);
	}
	
	@Test 
	public void testRemove1() {
		m.add("a");
		m.add("a",5);
		Assert.assertTrue(m.remove("a"));
		Assert.assertTrue(m.remove("a",3));
		assertEquals(2, m.count("a"));
		
		Assert.assertFalse(m.remove("b"));
		m.add("b",2);
		Assert.assertTrue(m.remove("b"));
		assertEquals(1, m.count("b"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		Assert.assertFalse(m.remove("a"));
		m.remove("a",-1);
	}
	
	@Test
	public void testAddRemove0(){
		Assert.assertTrue(m.add("a",0));
		assertEquals(0,m.size());
		Assert.assertTrue(m.remove("a",0));
		assertEquals(0,m.size());
	}
	
	@Test
	public void testCount() {
		assertEquals(0,m.count("m"));
	}
	
	@Test
	public void testSize() {
		assertEquals(0, m.size());
		m.add("a",5);
		assertEquals(5, m.size());
	}
	
	@Test
	public void testToString() {
		m.add("a",2);
		m.add("b");
		String res="[a:2; b:1; ]";
		assertEquals(res,m.toString());
	}
	
	@Test
	public void testClear() {
		m.add("a",2);
		m.clear();
		assertEquals(0,m.size());
	}
}
