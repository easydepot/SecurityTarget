package core.sfr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class TestSFRList {
	
	
	SFRList sfrlist;
	@Test
	public void test_getListOfAssignement_returns_emptylist() {
		given_an_SFRList();
		
		ArrayList<SFRElementAssignement> listOfAssignement =sfrlist.getListOfAssignement();
		assertTrue(listOfAssignement.isEmpty());
		
	}

	private void given_an_SFRList() {
		sfrlist = new SFRList();
	}
	
	@Test
	public void test_get_list_of_uncovered_dependencies_is_not_null(){
		given_an_SFRList();
		assertNotNull(sfrlist.getListOfUncoveredDependency());
		
		
		
	}
	
	@Test
	public void test_get_list_of_uncovered_dependencies_is_empty_by_default(){
		given_an_SFRList();
		assert_that_uncovered_dependency_list_is_empty();		
	}

	private void assert_that_uncovered_dependency_list_is_empty() {
		assertTrue(sfrlist.getListOfUncoveredDependency().isEmpty());
	}
	
	private void assert_that_uncovered_dependency_list_is_not_empty() {
		assertFalse(sfrlist.getListOfUncoveredDependency().isEmpty());
	}
	
	@Test
	public void test_get_list_of_uncovered_dependencies_is_empty_when_adding_non_dependant_sfr(){
		given_an_SFRList();
		when_adding_a_undependent_sfr();
		assert_that_uncovered_dependency_list_is_empty();		
	}
	
	@Test
	public void test_get_list_of_uncovered_dependencies_is_not_empty_when_adding_dependant_sfr(){
		given_an_SFRList();
		when_adding_a_dependent_sfr();
		assert_that_uncovered_dependency_list_is_not_empty();		
	}

	private void when_adding_a_undependent_sfr() {
		SFR sfr = new SFR("FMT_MTD.1");
		this.sfrlist.add(sfr);
	}
	
	private void when_adding_a_dependent_sfr() {
		SFR sfr = new SFR("FMT_MTD.1");
		sfr.addSFRDependency("dependency_id");
		this.sfrlist.add(sfr);
	}
	
	
	@Test
	public void test_getListOfAssignement_returns_list_of_assignement() {
		SFR sfr = new SFR("FMT_MTD.1");
		SFRElement e = new SFRElement("id");
		SFRElement e2 = new SFRElement("id");
		e.addNewAssignement();
		e2.addNewAssignement();
		sfr.addSFRElement(e);
		sfr.addSFRElement(e2);
		SFR sfr2 = new SFR("FMT_MTD.1");
		SFRElement e3 = new SFRElement("id");
		SFRElement e4 = new SFRElement("id");
		e3.addNewAssignement();
		e4.addNewAssignement();
		sfr2.addSFRElement(e3);
		sfr2.addSFRElement(e4);
		SFRList l = new SFRList();
		l.add(sfr);
		l.add(sfr2);
		

		ArrayList<SFRElementAssignement> listOfAssignement =l.getListOfAssignement();
		assertFalse(listOfAssignement.isEmpty());
	}
}
