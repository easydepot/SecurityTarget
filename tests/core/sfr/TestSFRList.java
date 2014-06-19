package core.sfr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;


public class TestSFRList {
	
	@Test
	public void test_getListOfAssignement_returns_emptylist() {
		SFRList sfrlist = new SFRList();
		
		ArrayList<SFRElementAssignement> listOfAssignement =sfrlist.getListOfAssignement();
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
