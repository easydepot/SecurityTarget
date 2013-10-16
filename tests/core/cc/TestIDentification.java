package core.cc;

import junit.framework.Assert;

import org.junit.Test;

public class TestIDentification {
	
	@Test 
	public void test_author() {
		Identification id = new Identification();
		id.setAuthor("J. Groslambert");
		Assert.assertEquals("J. Groslambert",id.getAuthor());
		
	}
	
	@Test 
	public void test_revision() {
		Identification id = new Identification();
		id.setCc_revision("R3");
		Assert.assertEquals("R3",id.getCc_revision());
		
	}
	
	@Test 
	public void test_version() {
		Identification id = new Identification();
		id.setCc_version("3.1");
		Assert.assertEquals("3.1",id.getCc_version());
		
	}
	
	@Test 
	public void test_date() {
		Identification id = new Identification();
		id.setDate("31/10/2013");
		Assert.assertEquals("31/10/2013",id.getDate());
		
	}
	
	@Test 
	public void test_Docversion() {
		Identification id = new Identification();
		id.setVersion("1.0");
		Assert.assertEquals("1.0",id.getVersion());
		
	}
	
	@Test
	public void test_sponsors() {
		Identification id = new Identification();
		id.setSponsor("sponsor1");
		Assert.assertEquals("sponsor1",id.getSponsor());
	}


	@Test
	public void test_reference() {
		Identification id = new Identification();
		id.setReference("ref");
		Assert.assertEquals("ref",id.getReference());
	}
	
	@Test
	public void test_title() {
		Identification id = new Identification();
		id.setTitle("title");
		Assert.assertEquals("title",id.getTitle());
	}



}
