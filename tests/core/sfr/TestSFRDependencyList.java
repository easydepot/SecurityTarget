package core.sfr;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSFRDependencyList {

	
	SFRDependencyList sut;
	
	@Test
	public void test_getListOfUncoveredDependencies_does_not_return_null() {
		given_a_SFRDepencyList();
		assertNotNull(sut.getListOfUncoveredDependencies());
		
	}

	private void given_a_SFRDepencyList() {
		sut =  new SFRDependencyList();
	}
	
	@Test
	public void test_getListOfUncoveredDependencies_returns_emply_list_by_default() {
		given_a_SFRDepencyList();
		assert_that_list_of_uncovered_depency_is_empty();
	}

	private void assert_that_list_of_uncovered_depency_is_empty() {
		assertTrue(sut.getListOfUncoveredDependencies().isEmpty());
	}
	
	@Test
	public void test_getListOfUncoveredDependencies_returns_non_empty_list_when_adding_an_uncovered_dependency() {
		given_a_SFRDepencyList();
		when_adding_a_dependency();
		assert_that_the_list_of_uncovered_dependency_is_not_empty();
		
	}
	
	@Test
	public void test_getListOfUncoveredDependencies_returns_non_empty_list_when_adding_a_covered_dependency() throws Exception {
		given_a_SFRDepencyList();
		when_adding_a_dependency();
		when_covering_the_dependency();
		assert_that_list_of_uncovered_depency_is_empty();
		
	}

	private void when_covering_the_dependency() throws Exception {
		sut.get(0).coverBySFR(new SFR("dependency_ID"));
	}

	private void assert_that_the_list_of_uncovered_dependency_is_not_empty() {
		assertFalse(sut.getListOfUncoveredDependencies().isEmpty());
	}

	private void when_adding_a_dependency() {
		sut.addDependency("dependency_ID");
	}

}
