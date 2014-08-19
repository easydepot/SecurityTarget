package core.cc;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import core.sfr.SFR;


public class TestSFRDependenciesResolver {
	
	SFRDependenciesResolver sut;
	SecurityTarget st;

	@Test
	public void test_processDependencies() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family1");
		st.listOfSFR.addSFRInstance("family2");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().clear();
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
		sut.processDependencies();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_is_ignored_when_sfr_instance_is_different() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family1","instanceA");
		st.listOfSFR.addSFRInstance("family2","instanceB");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().clear();
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
		sut.processDependencies();
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_is_not_ignored_when_instances_are_equals() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family1","instanceA");
		st.listOfSFR.addSFRInstance("family2","instanceA");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().clear();
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
		sut.processDependencies();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_with_no_matching_dependency() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family1");
		st.listOfSFR.addSFRInstance("family3");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().clear();
		assertEquals(2,st.listOfSFR.getListOfTOESFR().size());
		assertTrue(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().isEmpty());
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
		sut.processDependencies();
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_search_in_the_complete_list_of_SFR_to_find_the_matching_one() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family1");
		st.listOfSFR.addSFRInstance("family3");
		st.listOfSFR.addSFRInstance("family2");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().clear();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).getCoveringSFRs().isEmpty());
		assertFalse(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
		sut.processDependencies();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(0).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_applies_to_all_SFRs() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family2");
		st.listOfSFR.addSFRInstance("family1");
		st.listOfSFR.addSFRInstance("family3");
		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(1).getListOfDependancies().get(0).getCoveringSFRs().clear();
		sut.processDependencies();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(1).getListOfDependancies().get(0).isCovered());
	}
	
	@Test
	public void test_processDependencies_applies_to_all_dependencies() throws Exception {
		given_a_security_target();
		st.listOfSFR.addSFRInstance("family2");
		st.listOfSFR.addSFRInstance("family1");
		st.listOfSFR.addSFRInstance("family3");
		st.listOfSFR.addSFRInstance("family4");

		given_a_dependency_resolver();
		//FIXME : this step should not be performed. PErhaps something wrong in clone.
		st.getSFRPart().getListOfTOESFR().get(1).getListOfDependancies().get(0).getCoveringSFRs().clear();
		sut.processDependencies();
		assertTrue(st.getSFRPart().getListOfTOESFR().get(1).getListOfDependancies().get(1).isCovered());
	}

	private void given_a_dependency_resolver() {
		sut = new SFRDependenciesResolver(st);
	}

	private void given_a_security_target() throws SAXException, IOException,
			ParserConfigurationException {
		st = new SecurityTarget();
	}

	@BeforeClass
	static public void given_a_sfr_catalog() {
		SFR.addNewSFR("family1");
		SFR.addNewSFR("family2");
		SFR.addNewSFR("family3");
		SFR.addNewSFR("family4");
		SFR.listOfSFR.get(0).addSFRDependency("family2");
		SFR.listOfSFR.get(0).addSFRDependency("family4");


	}
}
