package core.sfr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import core.securityObjective.SecurityObjective;
import core.sfr.SFR;
import core.sfr.SFRElement;
import core.sfr.SFRElementAssignement;

public class TestSFR {

	private static final String SFR_ID = "sfr_id";




	@Test
	public void testGetIdent1() {
		SFR sfr = givenASFR();
		assertEquals("FMT_MTD.1", sfr.getFullIdent());

	}
	
	SFR sfr;
	
	


	private SFR givenASFR() {
	    sfr = new SFR("FMT_MTD.1",null,"Management Management of TSF data");
		return sfr;
	}
	
	SFRDependencyList returned_sfr_dependency;
	
	
	@Test
	public void testGetListOfDependances_does_not_return_a_null_value_by_default() {
		givenASFR();
		when_asking_for_dependances();
		assert_that_returned_dependancy_list_is_not_null();
		
	}
	
	@Test
	public void testGetListOfCoveredObjective_returns_an_empty_list_of_objective_by_default() {
		givenASFR();
		assertNotNull(this.sfr.getListOfCoveredObjectives());
		assertTrue(this.sfr.getListOfCoveredObjectives().isEmpty());
	}
	SecurityObjective obj;
	
	@Test
	public void testGetListOfCoveredObjective_returns_an_non_empty_list_after_adding_objectives() {
		givenASFR();
		given_a_security_objective();
		when_covering_the_objective();
		assertNotNull(this.sfr.getListOfCoveredObjectives());
		assertFalse(this.sfr.getListOfCoveredObjectives().isEmpty());
		assertEquals(obj,sfr.getListOfCoveredObjectives().get(0));
		
	}

	private void when_covering_the_objective() {
		sfr.coverSecurityObjective(obj);
	}
	
	@Test
	public void test_is_covered_return_true_when_the_objective_is_covered_by_the_sfr() {
		givenASFR();
		given_a_security_objective();
		when_covering_the_objective();	
		assertTrue(sfr.isCovered(obj));
	}
	
	@Test
	public void test_is_covered_return_false_when_the_objective_is_not_covered_by_the_sfr() {
		givenASFR();
		given_a_security_objective();
		assertFalse(sfr.isCovered(obj));
	}

	private void given_a_security_objective() {
		obj = new SecurityObjective("id_security_objective");
	}
	
	@Test
	public void testGetListOfDependances_returns_an_empty_list_by_default() {
		givenASFR();
		when_asking_for_dependances();
		assert_that_returned_sfr_dependency_list_is_empty();	
	}
	
	@Test
	public void testGetListOfDependances_returns_an_non_empty_list_after_adding_a_dependency() {
		givenASFR();
		when_adding_a_sfr_dependency();
		when_asking_for_dependances();
		assert_that_returned_sfr_dependency_list_is_not_empty();	
	}
	
	@Test
	public void testGetListOfDependances_returns_a_first_element_in_conformity_with_the_adding() {
		givenASFR();
		when_adding_a_sfr_dependency();
		when_asking_for_dependances();
		assertEquals(this.returned_sfr_dependency.get(0).getFamily(),SFR_ID);;
	}

	private void when_adding_a_sfr_dependency() {
		sfr.addSFRDependency(SFR_ID);
	}


	private void assert_that_returned_sfr_dependency_list_is_empty() {
		assertTrue(returned_sfr_dependency.isEmpty());
	}
	
	private void assert_that_returned_sfr_dependency_list_is_not_empty() {
		assertFalse(returned_sfr_dependency.isEmpty());
	}


	private void assert_that_returned_dependancy_list_is_not_null() {
		assertNotNull(returned_sfr_dependency);
	}


	private void when_asking_for_dependances() {
		returned_sfr_dependency = sfr.getListOfDependancies();
	}
	
	
	@Test
	public void testGetIdent_lowerCase() {
		SFR sfr = new SFR("fmt_mtd.1",null,"Management Management of TSF data");
		assertEquals("FMT_MTD.1", sfr.getFullIdent());

	}
	
	@Test
	public void testInstanciedIdent_lowerCase() {
		SFR sfr = new SFR(null,"fmt_mtd.1","Management Management of TSF data");
		sfr.instance = "i1";
		assertEquals("FMT_MTD.1/i1", sfr.getInstanciedIdent());

	}
	
	@Test
	public void testGetIdent_getIdent() {
		SFR sfr = new SFR(null,"fmt_mtd.1","Management Management of TSF data");
		assertEquals("FMT_MTD.1", sfr.getIdent());

	}
	
	
	
	@Test
	public void testAddSFRElement() {
		SFR sfr = new SFR("FMT_MTD.1");
		sfr.addSFRElement(new SFRElement("id"));
		Assert.assertTrue(sfr.hasElements());
	}
	
	@Test
	public void test_that_method_has_element_returns_false_by_default() {
		SFR sfr = new SFR("FMT_MTD.1");
		Assert.assertFalse(sfr.hasElements());
	}
	
	
	
	@Test
	public void testGetIdent2() {
		SFR sfr = new SFR("FMT_MTD.1","ident","Management Management of TSF data");
		Assert.assertEquals("FMT_MTD.1/ident", sfr.getFullIdent());

	}
	
	@Test
	public void testGetIdent() {
		SFR sfr = new SFR("FMT_MTD.1","ident","Management Management of TSF data");
		Assert.assertEquals("ident".toUpperCase(), sfr.getIdent());
	}
	
	@Test
	public void test_getListOfAssignement_returns_emptylist() {
		SFR sfr = new SFR("FMT_MTD.1");
		
		ArrayList<SFRElementAssignement> listOfAssignement =sfr.getListOfAssignement();
		assertTrue(listOfAssignement.isEmpty());
		
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

		ArrayList<SFRElementAssignement> listOfAssignement =sfr.getListOfAssignement();
		assertFalse(listOfAssignement.isEmpty());
	}
	
	
	
	

}
