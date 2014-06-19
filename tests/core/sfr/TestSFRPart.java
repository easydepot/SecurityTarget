package core.sfr;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestSFRPart {
	
	SFRPart sut;
	SFR result;
	
	@Test
	public void test_find_with_only_family() throws Exception {
		givenAnSFRCatalog();
		givenASFRPart();
		when_adding_a_sfr_from_the_catalog();
		result = sut.findSFRbyID("fdp_etc.1",null);
		assertEquals("fdp_etc.1",result.ident);
	}
	
	@Test
	public void test_find_with_family_and_ident() throws Exception {
		givenAnSFRCatalog();
		givenASFRPart();
		when_adding_a_sfr_from_the_catalog_with_an_instance();
		result = sut.findSFRbyID("fdp_etc.1","instance");
		assertEquals("fdp_etc.1",result.ident);
	}

	private void when_adding_a_sfr_from_the_catalog() throws Exception {
		sut.addSFRInstance("fdp_etc.1");
	}
	
	private void when_adding_a_sfr_from_the_catalog_with_an_instance() throws Exception {
		sut.addSFRInstance("fdp_etc.1","instance");
	}

	private void givenAnSFRCatalog() {
		SFR.addNewSFR("fdp_etc.1");
	}

	private void givenASFRPart() {
		sut =  new SFRPart();
	}
	
	@Test
	public void test_contains() {
		givenASFRPart();
		assertFalse(sut.contains("fdp_etc.1"));
		
	}
	
	@Test
	public void test_contains_return_true_when_the_sfr_has_been_added() throws Exception {
		givenASFRPart();
		givenAnSFRCatalog();
		givenASFRPart();
		when_adding_a_sfr_from_the_catalog();
		assertTrue(sut.contains("fdp_etc.1"));
		
	}
	
	@Test
	public void test_that_two_SFR_with_the_same_id_cannot_be_added() throws Exception {
		givenAnSFRCatalog();
		givenASFRPart();
		when_adding_a_sfr_from_the_catalog();
		
		try {
			when_adding_a_sfr_from_the_catalog();
			fail("must throw an exception");
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}

}
