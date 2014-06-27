package core.cc;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class TestOSP {
	
	OSP sut;
	
	@Test 
	public void test_hasDescription_case_positive() {
		sut = new OSP("id");
		sut.setDescription("description");
		assertTrue(sut.hasDescription());
		
		
	}
	
	@Test 
	public void test_hasDescription_case_negative() {
		sut = new OSP("id");
		assertFalse(sut.hasDescription());
		
		
	}

}
