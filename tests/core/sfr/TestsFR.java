package core.sfr;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import core.sfr.SFR;
import core.sfr.SFRElement;
import core.sfr.SFRElementAssignement;

public class TestsFR {

	@Test
	public void testGetIdent1() {
		SFR sfr = new SFR("FMT_MTD.1",null,"Management Management of TSF data");
		Assert.assertEquals("FMT_MTD.1", sfr.getFullIdent());

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
		Assert.assertEquals("ident", sfr.getIdent());
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
