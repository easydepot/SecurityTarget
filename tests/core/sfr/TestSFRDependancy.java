package core.sfr;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSFRDependancy {

	private static final String SFR_ID = "SFR_ID";
	SFRDependency sut;
	boolean exception_thrown = false;
	
	
	@Test
	public void test_that_SFRDependancy_Constructor_set_the_family() {
		given_a_SFRDependency();
		assert_that_getFamily_returns_the_ID_given_within_the_constructor();
	}

	
	
	@Test
	public void test_that_SFRDependancy_is_not_covered_by_default() {
		given_a_SFRDependency();
		assert_that_the_SFRDependancy_is_not_covered();
	}

	
	
	@Test
	public void test_that_SFRDependancy_can_be_covered() throws Exception {
		given_a_SFRDependency();
		when_adding_a_SFR_to_cover_the_dependancy();
		assert_that_dependency_is_covered();
	}



	private void assert_that_dependency_is_covered() {
		assertTrue(sut.isCovered());
	}

	
	
	@Test
	public void test_that_SFRDependancy_cant_be_covered_by_another_ID() throws Exception {
		given_a_SFRDependency();
		when_coverering_by_an_SFR_with_another_ID();
		assert_That_An_Exception_Is_Thrown();
		assert_that_the_SFRDependancy_is_not_covered();
		
	}



	private void assert_That_An_Exception_Is_Thrown() {
		assertTrue(this.exception_thrown);
		this.exception_thrown = false;
	}



	private void when_coverering_by_an_SFR_with_another_ID() throws Exception {
		SFR.addNewSFR("another_id");
		SFR sfr = SFR.getNewSFRInstance("another_id");
		
		try {
			sut.coverBySFR(sfr);
		} catch (Exception e) {
			this.exception_thrown = true;
		}
	}
	
	private void when_adding_a_SFR_to_cover_the_dependancy() throws Exception {
		SFR.addNewSFR(SFR_ID);
		SFR sfr = SFR.getNewSFRInstance(SFR_ID);
		sut.coverBySFR(sfr);
	}
	private void assert_that_the_SFRDependancy_is_not_covered() {
		assertFalse(sut.isCovered());
	}
	
	private void assert_that_getFamily_returns_the_ID_given_within_the_constructor() {
		assertEquals(sut.getFamily(), SFR_ID);
	}

	private void given_a_SFRDependency() {
		sut = new SFRDependency(SFR_ID);
	}
}
